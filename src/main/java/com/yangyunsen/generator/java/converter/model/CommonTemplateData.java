package com.yangyunsen.generator.java.converter.model;

/**
 * 通用模板数据
 *
 * @author CloudS3n
 * @date 2021-10-09 10:47
 */
public interface CommonTemplateData {

    /**
     * 获取包名
     *
     * @return 包名
     */
    String getPkgName();

    /**
     * 获取类名
     *
     * @return 获取类名
     */
    String getClassName();
}
