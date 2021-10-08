package com.yangyunsen.generator.java.converter;

import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.converter.impl.jpa.entity.JpaEntityConverterStrategy;
import com.yangyunsen.generator.java.converter.model.EntityTemplateData;
import com.yangyunsen.generator.java.dbloader.oracle.OracleColumnInfo;

import java.util.List;
import java.util.Map;

/**
 * @author clouds3n
 * @date 2021-09-29
 */
public class Converter {

    public static List<EntityTemplateData> convert(GeneratorConfig generatorConfig, Map<String, List<OracleColumnInfo>> tableColumnsMap) {
        return new EntityConverterContext(new JpaEntityConverterStrategy()).convertEntity(generatorConfig, tableColumnsMap);
    }
}
