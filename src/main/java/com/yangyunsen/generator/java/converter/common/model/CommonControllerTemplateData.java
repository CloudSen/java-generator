package com.yangyunsen.generator.java.converter.common.model;

import com.yangyunsen.generator.java.converter.ControllerTemplateData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 通用Controller模板数据类
 *
 * @author CloudS3n
 * @date 2021-10-08 12:35
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CommonControllerTemplateData implements ControllerTemplateData {

    /**
     * 包名
     */
    private String pkgName;
    /**
     * 导入的service包名
     */
    private String importServicePkgName;
    /**
     * controller request mapping对应的url
     */
    private String controllerUrl;
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
