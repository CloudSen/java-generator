package com.yangyunsen.generator.java.converter;

import com.yangyunsen.generator.java.common.mapping.DefaultJavaTypePkgMapping;
import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.dbloader.oracle.OracleColumnInfo;

import java.util.List;
import java.util.Map;

/**
 * 代表这是一个转换器
 *
 * @author clouds3n
 * @date 2021-09-29
 */
public interface ConverterStrategy {

    /**
     * 将表数据转换为模板需要的数据
     *
     * @param generatorConfig 全局配置
     * @param tableColumnsMap 表字段信息
     * @return 模板数据列表
     */
    List<EntityTemplateData> convert(GeneratorConfig generatorConfig, Map<String, List<OracleColumnInfo>> tableColumnsMap);

    /**
     * 数据库字段类型转java类型
     *
     * @param columnInfo 数据库字段信息
     * @return java类型信息
     */
    DefaultJavaTypePkgMapping convertDbTypeToJavaType(OracleColumnInfo columnInfo);
}
