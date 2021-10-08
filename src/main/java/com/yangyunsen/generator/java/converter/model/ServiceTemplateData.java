package com.yangyunsen.generator.java.converter.model;

/**
 * @author CloudS3n
 * @date 2021-10-08 12:57
 */
public interface ServiceTemplateData extends ClassCommentTemplateData {

    /**
     * 获取controller包名
     *
     * @return controller包名
     */
    String getPkgName();
}
