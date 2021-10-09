package com.yangyunsen.generator.java.converter;

import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.converter.model.ServiceTemplateData;

import java.util.List;

/**
 * 代表这是一个Service接口转换器
 *
 * @author CloudS3n
 * @date 2021-10-09 14:09
 */
public interface ServiceConverterStrategy {

    /**
     * 提取全局配置，生成控制器模板数据s
     *
     * @param generatorConfig 全局配置
     * @return 控制器模板数据列表
     */
    List<ServiceTemplateData> convert(GeneratorConfig generatorConfig);
}
