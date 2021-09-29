package com.yangyunsen.generator.java.converter.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author clouds3n
 * @date 2021-09-27
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class EntityField {

    /**
     * 是否为主键
     */
    private Boolean pkFlg;
    /**
     * java类型
     */
    private String javaType;
    /**
     * 数据库字段名
     */
    private String dbName;
    /**
     * java变量名
     */
    private String javaName;
}
