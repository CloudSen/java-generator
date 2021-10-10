package com.yangyunsen.generator.java.generator;

import freemarker.template.TemplateException;

import java.io.IOException;

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
     *
     * @throws TemplateException 代码模板
     * @throws IOException       文件读取异常
     */
    default void generateEntity() throws TemplateException, IOException {
    }

    /**
     * 生成DTO类文件
     *
     * @throws TemplateException 模板异常
     * @throws IOException       文件读取异常
     */
    default void generateDTO() throws TemplateException, IOException {
    }

    /**
     * 生成Repo接口类文件
     *
     * @throws TemplateException 模板异常
     * @throws IOException       文件读取异常
     */
    default void generateRepo() throws TemplateException, IOException {
    }

    /**
     * 生成Custom Repo接口类文件 for native jpa
     *
     * @throws TemplateException 模板异常
     * @throws IOException       文件读取异常
     */
    default void generateCustomRepo() throws TemplateException, IOException {
    }

    /**
     * 生成Custom RepoImpl类文件 for native jpa
     *
     * @throws TemplateException 模板异常
     * @throws IOException       文件读取异常
     */
    default void generateCustomRepoImpl() throws TemplateException, IOException {
    }

    /**
     * 生成Service接口类文件
     *
     * @throws TemplateException 模板异常
     * @throws IOException       文件读取异常
     */
    default void generateService() throws TemplateException, IOException {
    }

    /**
     * 生成ServiceImpl接口类文件
     *
     * @throws TemplateException 模板异常
     * @throws IOException       文件读取异常
     */
    default void generateServiceImpl() throws TemplateException, IOException {
    }

    /**
     * 生成Controller接口类文件
     *
     * @throws TemplateException 模板异常
     * @throws IOException       文件读取异常
     */
    default void generateController() throws TemplateException, IOException {
    }

}
