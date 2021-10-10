package com.yangyunsen.generator.java.converter;

import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.converter.model.ServiceTemplateData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author CloudS3n
 * @date 2021-10-09 14:20
 */
@Getter
@RequiredArgsConstructor
public class ServiceConverterContext {

    private final ServiceConverterStrategy serviceConverterStrategy;

    public List<ServiceTemplateData> convertService(GeneratorConfig generatorConfig) {
        return serviceConverterStrategy.convert(generatorConfig);
    }
}
