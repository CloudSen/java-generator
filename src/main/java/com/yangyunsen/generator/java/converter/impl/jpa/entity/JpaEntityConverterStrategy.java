package com.yangyunsen.generator.java.converter.impl.jpa.entity;

import cn.hutool.core.collection.CollectionUtil;
import com.yangyunsen.generator.java.common.mapping.DefaultJavaTypePkgMapping;
import com.yangyunsen.generator.java.common.mapping.JavaTypePkgMapping;
import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.common.model.dto.PackageInfo;
import com.yangyunsen.generator.java.common.model.enums.MvcLevel;
import com.yangyunsen.generator.java.common.model.statics.CommonStatic;
import com.yangyunsen.generator.java.converter.EntityConverterStrategy;
import com.yangyunsen.generator.java.converter.model.EntityTemplateData;
import com.yangyunsen.generator.java.converter.model.jpa.EntityField;
import com.yangyunsen.generator.java.converter.model.jpa.JpaEntityTemplateData;
import com.yangyunsen.generator.java.dbloader.oracle.OracleColumnInfo;
import com.yangyunsen.generator.java.util.GeneratorDateUtil;
import com.yangyunsen.generator.java.util.GeneratorStringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ORM JPA实体类转换器
 *
 * @author clouds3n
 * @date 2021-09-27
 */
public class JpaEntityConverterStrategy implements EntityConverterStrategy {

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
                .setClassName(GeneratorStringUtil.tableNameToClassName(tableName, MvcLevel.ENTITY));
            if (CollectionUtil.isNotEmpty(columns)) {
                List<EntityField> entityFields = new ArrayList<>();
                List<String> importPkgNames = new ArrayList<>();
                columns.forEach(columnInfo -> {
                    JavaTypePkgMapping javaTypePkgMapping = convertDbTypeToJavaType(columnInfo);
                    if (StringUtils.isNotBlank(javaTypePkgMapping.getPkgName())) {
                        importPkgNames.add(javaTypePkgMapping.getPkgName());
                    }
                    entityFields.add(new EntityField().setPkFlg(columnInfo.getPkFlag())
                        .setDbName(columnInfo.getColumnName())
                        .setJavaType(javaTypePkgMapping.getJavaType())
                        .setJavaName(GeneratorStringUtil.dbColumnNameToJavaName(columnInfo.getColumnName())));
                });
                jpaEntityTemplateData.setImportPkgNames(importPkgNames)
                    .setFields(entityFields);
                entityTemplateDataList.add(jpaEntityTemplateData);
            }
        });
        return entityTemplateDataList;
    }

    @Override
    public JavaTypePkgMapping convertDbTypeToJavaType(OracleColumnInfo columnInfo) {
        String dbType = StringUtils.lowerCase(columnInfo.getDataType());
        if (StringUtils.containsAny(dbType, CommonStatic.DATE_STR, CommonStatic.TIMESTAMP_STR)) {
            return DefaultJavaTypePkgMapping.LOCAL_DATE_TIME;
        } else if (StringUtils.contains(dbType, CommonStatic.NUMBER_STR)) {
            if (columnInfo.getDataScale() == 0 && columnInfo.getDataPrecision() == 0) {
                return DefaultJavaTypePkgMapping.LONG;
            } else if (columnInfo.getDataScale() > 0 && columnInfo.getDataPrecision() < CommonStatic.INT_10) {
                return DefaultJavaTypePkgMapping.DOUBLE;
            } else if (columnInfo.getDataScale() > 0 && columnInfo.getDataPrecision() >= CommonStatic.INT_10) {
                return DefaultJavaTypePkgMapping.BIG_DECIMAL;
            } else if (columnInfo.getDataPrecision() == 1) {
                return DefaultJavaTypePkgMapping.BOOLEAN;
            } else if (columnInfo.getDataPrecision() < CommonStatic.INT_10) {
                return DefaultJavaTypePkgMapping.INTEGER;
            } else if (columnInfo.getDataPrecision() >= CommonStatic.INT_10 && columnInfo.getDataPrecision() < CommonStatic.INT_19) {
                return DefaultJavaTypePkgMapping.LONG;
            } else {
                return DefaultJavaTypePkgMapping.BIG_DECIMAL;
            }
        } else if (StringUtils.contains(dbType, CommonStatic.FLOAT_STR)) {
            return DefaultJavaTypePkgMapping.FLOAT;
        } else if (StringUtils.contains(dbType, CommonStatic.BLOB_STR)) {
            return DefaultJavaTypePkgMapping.BLOB;
        } else if (StringUtils.containsAny(dbType, CommonStatic.BINARY_STR, CommonStatic.RAW_STR)) {
            return DefaultJavaTypePkgMapping.BYTE_ARRAY;
        } else if (StringUtils.contains(dbType, CommonStatic.VARCHAR_STR) && columnInfo.getDataLength() == 1) {
            return DefaultJavaTypePkgMapping.BOOLEAN;
        } else {
            return DefaultJavaTypePkgMapping.STRING;
        }
    }
}
