package com.yangyunsen.generator.java.converter.impl.jpa;

import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.common.model.enums.MvcLevel;
import com.yangyunsen.generator.java.converter.ServiceImplConverterStrategy;
import com.yangyunsen.generator.java.converter.model.ServiceImplTemplateData;
import com.yangyunsen.generator.java.converter.model.jpa.JpaServiceImplTemplateData;
import com.yangyunsen.generator.java.util.GeneratorDateUtil;
import com.yangyunsen.generator.java.util.GeneratorStringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * JPA Service实现类模板数据转换器
 *
 * @author clouds3n
 * @date 2021-10-10
 */
public class JpaServiceImplConverter implements ServiceImplConverterStrategy {
    @Override
    public List<ServiceImplTemplateData> convert(GeneratorConfig generatorConfig) {
        List<ServiceImplTemplateData> serviceImplTemplateDataList = new ArrayList<>();
        String serviceImplPkgName = generatorConfig.getPackageInfo().getServiceImplPkgName();
        String author = generatorConfig.getAuthor();
        String commentDate = GeneratorDateUtil.getCommentDate();
        String parentPkgName = GeneratorStringUtil.getParentPkgName(serviceImplPkgName);
        generatorConfig.getTableNames().forEach(tableName -> {
            JpaServiceImplTemplateData jpaServiceImplTemplateData = new JpaServiceImplTemplateData()
                .setPkgName(serviceImplPkgName)
                .setServicePkgName(GeneratorStringUtil.getBrotherMvcPkgName(parentPkgName, tableName, MvcLevel.SERVICE))
                .setRepoPkgName(GeneratorStringUtil.getBrotherMvcPkgName(parentPkgName, tableName, MvcLevel.REPO))
                .setClassName(GeneratorStringUtil.tableNameToClassName(tableName, MvcLevel.SERVICE_IMPL))
                .setServiceClassName(GeneratorStringUtil.tableNameToClassName(tableName, MvcLevel.SERVICE))
                .setRepoClassName(GeneratorStringUtil.tableNameToClassName(tableName, MvcLevel.REPO))
                .setAuthor(author)
                .setCreateDate(commentDate);
            serviceImplTemplateDataList.add(jpaServiceImplTemplateData);
        });
        return serviceImplTemplateDataList;
    }
}
