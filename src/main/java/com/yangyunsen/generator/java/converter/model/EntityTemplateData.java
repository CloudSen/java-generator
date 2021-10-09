package com.yangyunsen.generator.java.converter.model;

import com.yangyunsen.generator.java.converter.model.jpa.EntityField;

import java.util.List;

/**
 * @author clouds3n
 * @date 2021-09-29
 */
public interface EntityTemplateData extends ClassCommentTemplateData, CommonTemplateData {


    /**
     * 获取要导入的包名
     *
     * @return 导入包名
     */
    List<String> getImportPkgNames();

    /**
     * 获取表名
     *
     * @return 表名
     */
    String getTableName();

    /**
     * 获取表字段列表
     *
     * @return 表字段列表
     */
    List<EntityField> getFields();
}
