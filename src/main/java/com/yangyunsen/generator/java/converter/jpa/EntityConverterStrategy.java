package com.yangyunsen.generator.java.converter.jpa;

import cn.hutool.core.collection.CollectionUtil;
import com.yangyunsen.generator.java.common.mapping.DefaultJavaTypePkgMapping;
import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.common.model.dto.PackageInfo;
import com.yangyunsen.generator.java.common.model.statics.CommonStatic;
import com.yangyunsen.generator.java.converter.ConverterStrategy;
import com.yangyunsen.generator.java.converter.EntityTemplateData;
import com.yangyunsen.generator.java.converter.jpa.model.EntityField;
import com.yangyunsen.generator.java.converter.jpa.model.JpaEntityTemplateData;
import com.yangyunsen.generator.java.dbloader.oracle.OracleColumnInfo;
import com.yangyunsen.generator.java.util.GeneratorDateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CaseUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ORM实体类转换器
 *
 * @author clouds3n
 * @date 2021-09-27
 */
public class EntityConverterStrategy implements ConverterStrategy {

    @Override
    public List<EntityTemplateData> convert(GeneratorConfig generatorConfig, Map<String, List<OracleColumnInfo>> tableColumnsMap) {
        final List<EntityTemplateData> entityTemplateDataList = new ArrayList<>();
        final PackageInfo packageInfo = generatorConfig.getPackageInfo();
        tableColumnsMap.forEach((tableName, columns) -> {
            JpaEntityTemplateData jpaEntityTemplateData = new JpaEntityTemplateData()
                .setAuthor(generatorConfig.getAuthor())
                .setCreateDate(GeneratorDateUtil.getCommentDate())
                .setPkgName(packageInfo.getEntityPkgName())
                .setTableName(tableName)
                .setClassName(CaseUtils.toCamelCase(tableName, true, CommonStatic.UL_C));
            if (CollectionUtil.isNotEmpty(columns)) {
                List<EntityField> entityFields = new ArrayList<>();
                List<String> importPkgNames = new ArrayList<>();
                columns.forEach(columnInfo -> {
                    DefaultJavaTypePkgMapping javaTypePkgMapping = convertDbTypeToJavaType(columnInfo);
                    if (StringUtils.isNotBlank(javaTypePkgMapping.getPkgName())) {
                        importPkgNames.add(javaTypePkgMapping.getPkgName());
                    }
                    entityFields.add(new EntityField().setPkFlg(columnInfo.getPkFlag())
                        .setDbName(columnInfo.getColumnName())
                        .setJavaType(javaTypePkgMapping.getJavaType())
                        .setJavaName(CaseUtils.toCamelCase(columnInfo.getColumnName(), false, CommonStatic.UL_C)));
                });
                jpaEntityTemplateData.setImportPkgNames(importPkgNames)
                    .setFields(entityFields);
                entityTemplateDataList.add(jpaEntityTemplateData);
            }
        });
        return entityTemplateDataList;
    }

    @Override
    public DefaultJavaTypePkgMapping convertDbTypeToJavaType(OracleColumnInfo columnInfo) {
        String dbType = StringUtils.lowerCase(columnInfo.getDataType());
        if (StringUtils.containsAny(dbType, "date", "timestamp")) {
            return DefaultJavaTypePkgMapping.LOCAL_DATE_TIME;
        } else if (StringUtils.contains(dbType, "number")) {
            if (columnInfo.getDataScale() == 0 && columnInfo.getDataPrecision() == 0) {
                return DefaultJavaTypePkgMapping.LONG;
            } else if (columnInfo.getDataScale() > 0 && columnInfo.getDataPrecision() < 10) {
                return DefaultJavaTypePkgMapping.DOUBLE;
            } else if (columnInfo.getDataScale() > 0 && columnInfo.getDataPrecision() >= 10) {
                return DefaultJavaTypePkgMapping.BIG_DECIMAL;
            } else if (columnInfo.getDataPrecision() == 1) {
                return DefaultJavaTypePkgMapping.BOOLEAN;
            } else if (columnInfo.getDataPrecision() < 10) {
                return DefaultJavaTypePkgMapping.INTEGER;
            } else if (columnInfo.getDataPrecision() >= 10 && columnInfo.getDataPrecision() < 19) {
                return DefaultJavaTypePkgMapping.LONG;
            } else {
                return DefaultJavaTypePkgMapping.BIG_DECIMAL;
            }
        } else if (StringUtils.contains(dbType, "float")) {
            return DefaultJavaTypePkgMapping.FLOAT;
        } else if (StringUtils.contains(dbType, "blob")) {
            return DefaultJavaTypePkgMapping.BLOB;
        } else if (StringUtils.containsAny(dbType, "binary", "raw")) {
            return DefaultJavaTypePkgMapping.BYTE_ARRAY;
        } else if (StringUtils.contains(dbType, "varchar") && columnInfo.getDataLength() == 1) {
            return DefaultJavaTypePkgMapping.BOOLEAN;
        } else {
            return DefaultJavaTypePkgMapping.STRING;
        }
    }
}
