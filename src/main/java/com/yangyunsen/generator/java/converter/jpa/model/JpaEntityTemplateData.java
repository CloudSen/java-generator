package com.yangyunsen.generator.java.converter.jpa.model;

import com.yangyunsen.generator.java.converter.EntityTemplateData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Entity信息类
 *
 * @author clouds3n
 * @date 2021-09-27
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class JpaEntityTemplateData implements EntityTemplateData {

    /**
     * 包名
     */
    private String pkgName;
    /**
     * 需要import的类型包名列表
     */
    private List<String> importPkgNames;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 类名
     */
    private String className;
    /**
     * 字段信息列表
     */
    private List<EntityField> fields;
}
