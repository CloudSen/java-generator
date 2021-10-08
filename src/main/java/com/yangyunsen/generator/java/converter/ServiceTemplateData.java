package com.yangyunsen.generator.java.converter;

/**
 * @author CloudS3n
 * @date 2021-10-08 12:57
 */
public interface ServiceTemplateData {

    /**
     * 获取controller包名
     *
     * @return controller包名
     */
    String getPkgName();
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
