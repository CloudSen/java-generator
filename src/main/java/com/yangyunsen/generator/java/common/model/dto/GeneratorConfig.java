package com.yangyunsen.generator.java.common.model.dto;

import com.yangyunsen.generator.java.common.model.enums.Mode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * 生成器配置
 *
 * @author clouds3n
 * @date 2021-09-29
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class GeneratorConfig {

    /**
     * 代码作者
     */
    private String author;
    /**
     * 生成器模式，JAP OR MP
     */
    private Mode mode;
    /**
     * 包信息
     */
    private PackageInfo packageInfo;
    /**
     * 数据库连接信息
     */
    private DatabaseInfo databaseInfo;
    /**
     * 需要生成的表名列表
     */
    private Set<String> tableNames;
    /**
     * 是否开启open api 3文档注解
     */
    private Boolean enableOpenApi3;
}
