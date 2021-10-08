package com.yangyunsen.generator.java.common.model.dto;

import com.yangyunsen.generator.java.converter.EntityConverterContext;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 转换器组件
 *
 * @author CloudS3n
 * @date 2021-10-08 14:45
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ConverterComponent {

    /**
     * ORM实体类组件配置
     */
    private EntityConverterContext entityConverterContext;
}
