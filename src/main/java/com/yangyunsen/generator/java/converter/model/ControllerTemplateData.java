package com.yangyunsen.generator.java.converter.model;

/**
 * @author CloudS3n
 * @date 2021-10-08 12:44
 */
public interface ControllerTemplateData extends ClassCommentTemplateData, CommonTemplateData {


    /**
     * 获取依赖的service包名
     *
     * @return 依赖的service包名
     */
    String getImportServicePkgName();

    /**
     * 获取控制器URL前缀
     *
     * @return 控制器URL前缀
     */
    String getControllerUrl();
}
