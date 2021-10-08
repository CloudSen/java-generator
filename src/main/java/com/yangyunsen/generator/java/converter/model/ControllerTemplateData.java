package com.yangyunsen.generator.java.converter.model;

/**
 * @author CloudS3n
 * @date 2021-10-08 12:44
 */
public interface ControllerTemplateData extends ClassCommentTemplateData {

    /**
     * 获取controller包名
     *
     * @return controller包名
     */
    String getPkgName();

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

    /**
     * 获取controller类名
     *
     * @return 获取controller类名
     */
    String getClassName();
}
