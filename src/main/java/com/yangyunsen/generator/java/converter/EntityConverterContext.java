package com.yangyunsen.generator.java.converter;

import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.converter.model.EntityTemplateData;
import com.yangyunsen.generator.java.dbloader.oracle.OracleColumnInfo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * 实体类转换上下文
 *
 * @author clouds3n
 * @date 2021-09-29
 */
@Getter
@RequiredArgsConstructor
public class EntityConverterContext {

    private final EntityConverterStrategy entityConverterStrategy;

    public List<EntityTemplateData> convertEntity(GeneratorConfig generatorConfig, Map<String, List<OracleColumnInfo>> tableColumnsMap) {
        return entityConverterStrategy.convert(generatorConfig, tableColumnsMap);
    }
}
