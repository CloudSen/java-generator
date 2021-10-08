package com.yangyunsen.generator.java.converter.model.common;

import com.yangyunsen.generator.java.converter.model.ServiceTemplateData;
import lombok.Data;

/**
 * 通用Service模板数据类
 *
 * @author CloudS3n
 * @date 2021-10-08 12:57
 */
@Data
public class CommonServiceTemplateData implements ServiceTemplateData {


    /**
     * 包名
     */
    private String pkgName;
    /**
     * 作者
     */
    private String author;
    /**
     * 生成时间
     */
    private String createDate;
}
