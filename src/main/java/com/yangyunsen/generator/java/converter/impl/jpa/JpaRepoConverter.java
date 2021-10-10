package com.yangyunsen.generator.java.converter.impl.jpa;

import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.common.model.enums.MvcLevel;
import com.yangyunsen.generator.java.converter.RepoConverterStrategy;
import com.yangyunsen.generator.java.converter.model.RepoTemplateData;
import com.yangyunsen.generator.java.converter.model.jpa.JpaRepoTemplateData;
import com.yangyunsen.generator.java.util.GeneratorDateUtil;
import com.yangyunsen.generator.java.util.GeneratorStringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author CloudS3n
 * @date 2021-10-09 17:11
 */
public class JpaRepoConverter implements RepoConverterStrategy {
    @Override
    public List<RepoTemplateData> convert(GeneratorConfig generatorConfig, Map<String, String> tablePkTypeMap) {
        List<RepoTemplateData> repoTemplateDataList = new ArrayList<>();
        String repoPkgName = generatorConfig.getPackageInfo().getRepoPkgName();
        String author = generatorConfig.getAuthor();
        String commentDate = GeneratorDateUtil.getCommentDate();
        String parentPkgName = GeneratorStringUtil.getParentPkgName(repoPkgName);
        generatorConfig.getTableNames().forEach(tableName -> {
            JpaRepoTemplateData jpaRepoTemplateData = new JpaRepoTemplateData()
                .setPkgName(repoPkgName)
                .setEntityPkgName(GeneratorStringUtil.getBrotherMvcPkgName(parentPkgName, tableName, MvcLevel.ENTITY))
                .setClassName(GeneratorStringUtil.tableNameToClassName(tableName, MvcLevel.REPO))
                .setEntityClassName(GeneratorStringUtil.tableNameToClassName(tableName, MvcLevel.ENTITY))
                .setPkJavaType(tablePkTypeMap.get(tableName))
                .setAuthor(author)
                .setCreateDate(commentDate);
            repoTemplateDataList.add(jpaRepoTemplateData);
        });
        return repoTemplateDataList;
    }
}
