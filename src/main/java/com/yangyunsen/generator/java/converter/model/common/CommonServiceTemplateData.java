package com.yangyunsen.generator.java.converter.model.common;

import com.yangyunsen.generator.java.converter.model.ServiceTemplateData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 通用Service模板数据类
 *
 * @author CloudS3n
 * @date 2021-10-08 12:57
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CommonServiceTemplateData implements ServiceTemplateData {


    /**
     * 包名
     */
    private String pkgName;
    /**
     * 类名
     */
    private String className;
    /**
     * 作者
     */
    private String author;
    /**
     * 生成时间
     */
    private String createDate;
}
