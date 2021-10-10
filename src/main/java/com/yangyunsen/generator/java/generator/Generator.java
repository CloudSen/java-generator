package com.yangyunsen.generator.java.generator;

/**
 * 生成器
 *
 * @author clouds3n
 * @date 2021-10-10
 */
public interface Generator {

    /**
     * 生成mvc相关的类文件
     */
    void generate();

    /**
     * 生成ORM Entity类文件
     */
    default void generateEntity() {
    }

    /**
     * 生成DTO类文件
     */
    default void generateDTO() {
    }

    /**
     * 生成Repo接口类文件
     */
    default void generateRepo() {
    }

    /**
     * 生成Custom Repo接口类文件 for native jpa
     */
    default void generateCustomRepo() {
    }

    /**
     * 生成Custom RepoImpl类文件 for native jpa
     */
    default void generateCustomRepoImpl() {
    }

    /**
     * 生成Service接口类文件
     */
    default void generateService() {
    }

    /**
     * 生成ServiceImpl接口类文件
     */
    default void generateServiceImpl() {
    }

    /**
     * 生成Controller接口类文件
     */
    default void generateController() {
    }

}
