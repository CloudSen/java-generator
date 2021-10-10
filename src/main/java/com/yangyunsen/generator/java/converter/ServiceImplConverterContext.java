package com.yangyunsen.generator.java.converter;

import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.converter.model.ServiceImplTemplateData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author clouds3n
 * @date 2021-10-10
 */
@Getter
@RequiredArgsConstructor
public class ServiceImplConverterContext {

    private final ServiceImplConverterStrategy serviceImplConverterStrategy;

    public List<ServiceImplTemplateData> convertServiceImpl(GeneratorConfig generatorConfig) {
        return serviceImplConverterStrategy.convert(generatorConfig);
    }
}
