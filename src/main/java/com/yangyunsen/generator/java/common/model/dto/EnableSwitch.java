package com.yangyunsen.generator.java.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 启用开关
 *
 * @author CloudS3n
 * @date 2021-10-08 16:52
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EnableSwitch {

    /**
     * 是否生成ORM实体类
     */
    private Boolean generateEntity = true;
    /**
     * 是否生成DTO
     */
    private Boolean generateDTO = true;
    /**
     * 是否生成Repository
     */
    private Boolean generateRepo = true;
    /**
     * 是否生成自定义Repository
     */
    private Boolean generateCustomRepo = true;
    /**
     * 是否生成自定义RepositoryImpl
     */
    private Boolean generateCustomRepoImpl = true;
    /**
     * 是否生成Service
     */
    private Boolean generateService = true;
    /**
     * 是否生成ServiceImpl
     */
    private Boolean generateServiceImpl = true;
    /**
     * 是否生成Controller
     */
    private Boolean generateController = true;
    /**
     * 是否生成OpenApi3文档注解
     */
    private Boolean generateOpenApi3 = false;
}
