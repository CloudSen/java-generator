package com.yangyunsen.generator.java.converter;

import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.converter.model.ServiceImplTemplateData;

import java.util.List;

/**
 * @author clouds3n
 * @date 2021-10-10
 */
public interface ServiceImplConverterStrategy {

    /**
     * 提取全局配置，生成ServiceImpl模板数据
     *
     * @param generatorConfig 全局配置
     * @return 控制器模板数据列表
     */
    List<ServiceImplTemplateData> convert(GeneratorConfig generatorConfig);
}
