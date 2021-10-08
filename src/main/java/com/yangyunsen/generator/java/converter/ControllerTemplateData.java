package com.yangyunsen.generator.java.converter;

/**
 * @author CloudS3n
 * @date 2021-10-08 12:44
 */
public interface ControllerTemplateData {

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

    /**
     * 获取代码作者
     *
     * @return 代码作者
     */
    String getAuthor();

    /**
     * 获取创建日期
     *
     * @return 创建日期
     */
    String getCreateDate();
}
