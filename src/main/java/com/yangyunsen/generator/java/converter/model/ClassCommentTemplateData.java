package com.yangyunsen.generator.java.converter.model;

/**
 * @author CloudS3n
 * @date 2021-09-30 14:26
 */
public interface ClassCommentTemplateData {

    /**
     * 获取代码作者
     * @return 作者名
     */
    String getAuthor();

    /**
     * 获取生成日期
     * @return 当前日期yyyy-mm-dd
     */
    String getCreateDate();
}
