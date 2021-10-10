package com.yangyunsen.generator.java.common.mapping;

import com.yangyunsen.generator.java.common.model.enums.Mode;

import java.util.List;

/**
 * @author clouds3n
 * @date 2021-09-29
 */
public interface TemplateMapping {

    /**
     * 支持的模式
     *
     * @return 模式列表
     */
    List<Mode> getModes();

    /**
     * 获取模板名
     *
     * @return 模板名
     */
    String getTemplateName();
}
