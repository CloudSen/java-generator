package com.yangyunsen.generator.java.converter.model.jpa;

import com.yangyunsen.generator.java.converter.model.RepoTemplateData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author CloudS3n
 * @date 2021-10-08 13:48
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class JpaRepoTemplateData implements RepoTemplateData {

    /**
     * 包名
     */
    private String pkgName;
    /**
     * 实体类包名
     */
    private String entityPkgName;
    /**
     * 类名
     */
    private String className;
    /**
     * 实体类类名
     */
    private String entityClassName;
    /**
     * 主键对应的java类型
     */
    private String pkJavaType;
    /**
     * 作者
     */
    private String author;
    /**
     * 生成时间
     */
    private String createDate;
}
