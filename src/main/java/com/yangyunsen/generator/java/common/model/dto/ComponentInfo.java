package com.yangyunsen.generator.java.common.model.dto;

import com.yangyunsen.generator.java.dbloader.DbLoaderContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 生成器组件配置信息
 *
 * @author CloudS3n
 * @date 2021-10-08 14:23
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ComponentInfo {

    /**
     * 数据加载器配置
     */
    private DbLoaderContext dbLoaderContext;

    /**
     * 转换器组件配置
     */
    private ConverterComponent converterComponent;
}
