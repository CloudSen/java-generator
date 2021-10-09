package com.yangyunsen.generator.java.converter.model;

/**
 * @author CloudS3n
 * @date 2021-10-08 13:44
 */
public interface RepoTemplateData extends ClassCommentTemplateData, CommonTemplateData {

    /**
     * 获取实体类包名
     *
     * @return 实体类包名
     */
    String getEntityPkgName();

    /**
     * 获取实体类类名
     *
     * @return 实体类类名
     */
    String getEntityClassName();

    /**
     * 获取主键对应的java类型
     *
     * @return 主键对应的java类型
     */
    String getPkJavaType();
}
