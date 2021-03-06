package com.yangyunsen.generator.java.converter;

import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.converter.model.*;
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

    public static List<ServiceTemplateData> convertService(GeneratorConfig generatorConfig) {
        ServiceConverterContext serviceConverterContext = generatorConfig.getComponentInfo()
            .getConverterComponent().getServiceConverterContext();
        return serviceConverterContext.convertService(generatorConfig);
    }

    public static List<ServiceImplTemplateData> convertServiceImpl(GeneratorConfig generatorConfig) {
        ServiceImplConverterContext serviceImplConverterContext = generatorConfig.getComponentInfo()
            .getConverterComponent().getServiceImplConverterContext();
        return serviceImplConverterContext.convertServiceImpl(generatorConfig);
    }

    public static List<RepoTemplateData> convertRepo(GeneratorConfig generatorConfig, Map<String, String> tablePkTypeMap) {
        RepoConverterContext repoConverterContext = generatorConfig.getComponentInfo()
            .getConverterComponent().getRepoConverterContext();
        return repoConverterContext.convertRepo(generatorConfig, tablePkTypeMap);
    }
}
