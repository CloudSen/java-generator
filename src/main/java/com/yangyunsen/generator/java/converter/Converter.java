package com.yangyunsen.generator.java.converter;

import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.converter.model.ControllerTemplateData;
import com.yangyunsen.generator.java.converter.model.EntityTemplateData;
import com.yangyunsen.generator.java.dbloader.oracle.OracleColumnInfo;

import java.util.List;
import java.util.Map;

/**
 * @author clouds3n
 * @date 2021-09-29
 */
public class Converter {

    public static List<EntityTemplateData> convertEntity(GeneratorConfig generatorConfig, Map<String, List<OracleColumnInfo>> tableColumnsMap) {
        EntityConverterContext entityConverterContext = generatorConfig.getComponentInfo()
            .getConverterComponent().getEntityConverterContext();
        return entityConverterContext.convertEntity(generatorConfig, tableColumnsMap);
    }

    public static List<ControllerTemplateData> convertController(GeneratorConfig generatorConfig) {
        ControllerConverterContext controllerConverterContext = generatorConfig.getComponentInfo()
            .getConverterComponent().getControllerConverterContext();
        return controllerConverterContext.convertController(generatorConfig);
    }
}
