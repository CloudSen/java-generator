package com.yangyunsen.generator.java.converter.model;

/**
 * @author CloudS3n
 * @date 2021-10-08 13:10
 */
public interface ServiceImplTemplateData extends ClassCommentTemplateData {

    /**
     * 获取包名
     *
     * @return 包名
     */
    String getPkgName();

    /**
     * 获取Service接口包名
     *
     * @return Service接口包名
     */
    String getInterfacePkgName();

    /**
     * 获取持久层接口包名
     *
     * @return 持久层接口包名
     */
    String getRepoPkgName();

    /**
     * 获取类名
     *
     * @return 类名
     */
    String getClassName();

    /**
     * 获取service接口类名
     *
     * @return service接口类名
     */
    String getInterfaceClassName();
}
