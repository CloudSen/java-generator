package com.yangyunsen.generator.java.converter.model.jpa;

import com.yangyunsen.generator.java.converter.model.ServiceImplTemplateData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author clouds3n
 * @date 2021-10-10
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class JpaServiceImplTemplateData implements ServiceImplTemplateData {

    /**
     * 包名
     */
    private String pkgName;
    /**
     * service包名
     */
    private String servicePkgName;
    /**
     * repo包名
     */
    private String repoPkgName;
    /**
     * 类名
     */
    private String className;
    /**
     * service类名
     */
    private String serviceClassName;
    /**
     * repo类名
     */
    private String repoClassName;
    /**
     * 作者
     */
    private String author;
    /**
     * 生成时间
     */
    private String createDate;
}
