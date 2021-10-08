package com.yangyunsen.generator.java.converter;

/**
 * @author CloudS3n
 * @date 2021-10-08 13:44
 */
public interface RepoTemplateData {

    /**
     * 获取包名
     *
     * @return 包名
     */
    String getPkgName();

    /**
     * 获取实体类包名
     *
     * @return 实体类包名
     */
    String getEntityPkgName();

    /**
     * 获取类名
     *
     * @return 类名
     */
    String getClassName();

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

    /**
     * 获取作者
     *
     * @return 作者
     */
    String getAuthor();

    /**
     * 获取生成时间
     *
     * @return 生成时间
     */
    String getCreateDate();
}
