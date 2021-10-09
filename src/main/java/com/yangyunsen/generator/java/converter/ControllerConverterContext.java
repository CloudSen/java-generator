package com.yangyunsen.generator.java.converter;

import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.converter.model.ControllerTemplateData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author CloudS3n
 * @date 2021-10-09 13:18
 */
@Getter
@RequiredArgsConstructor
public class ControllerConverterContext {

    private final ControllerConverterStrategy controllerConverterStrategy;

    public List<ControllerTemplateData> convertController(GeneratorConfig generatorConfig) {
        return controllerConverterStrategy.convert(generatorConfig);
    }
}
