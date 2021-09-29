package com.yangyunsen.generator.java.common.mapping;

/**
 * @author clouds3n
 * @date 2021-09-25
 */
public interface JavaTypePkgMapping {

    /**
     * 获取Java类型
     *
     * @return Java类型
     */
    String getJavaType();

    /**
     * 获取Java类型对应的包全名
     *
     * @return 包全名
     */
    String getPkgName();
}
