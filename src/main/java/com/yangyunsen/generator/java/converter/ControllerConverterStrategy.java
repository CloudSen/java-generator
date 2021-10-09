package com.yangyunsen.generator.java.converter;

import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.converter.model.ControllerTemplateData;

import java.util.List;

/**
 * 代表这是一个控制器转换器
 *
 * @author CloudS3n
 * @date 2021-10-09 09:20
 */
public interface ControllerConverterStrategy {

    /**
     * 提取全局配置，生成控制器模板数据
     *
     * @param generatorConfig 全局配置
     * @return 控制器模板数据列表
     */
    List<ControllerTemplateData> convert(GeneratorConfig generatorConfig);
}
