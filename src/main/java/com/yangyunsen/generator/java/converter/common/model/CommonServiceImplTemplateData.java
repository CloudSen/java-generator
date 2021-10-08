package com.yangyunsen.generator.java.converter.common.model;

import com.yangyunsen.generator.java.converter.ServiceImplTemplateData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 通用ServiceImpl模板数据类
 *
 * @author CloudS3n
 * @date 2021-10-08 13:14
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class CommonServiceImplTemplateData implements ServiceImplTemplateData {

    /**
     * 包名
     */
    private String pkgName;
    /**
     * service接口包名
     */
    private String interfacePkgName;
    /**
     * 持久层仓库包名
     */
    private String repoPkgName;
    /**
     * serviceImpl类名
     */
    private String className;
    /**
     * service接口类名
     */
    private String interfaceClassName;
    /**
     * 作者
     */
    private String author;
    /**
     * 生成时间
     */
    private String createDate;

}
