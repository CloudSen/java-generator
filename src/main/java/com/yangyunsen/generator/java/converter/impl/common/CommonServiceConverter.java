package com.yangyunsen.generator.java.converter.impl.common;

import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.common.model.enums.MvcLevel;
import com.yangyunsen.generator.java.converter.ServiceConverterStrategy;
import com.yangyunsen.generator.java.converter.model.ServiceTemplateData;
import com.yangyunsen.generator.java.converter.model.common.CommonServiceTemplateData;
import com.yangyunsen.generator.java.util.GeneratorDateUtil;
import com.yangyunsen.generator.java.util.GeneratorStringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CloudS3n
 * @date 2021-10-09 14:12
 */
public class CommonServiceConverter implements ServiceConverterStrategy {

    @Override
    public List<ServiceTemplateData> convert(GeneratorConfig generatorConfig) {
        List<ServiceTemplateData> serviceTemplateDataList = new ArrayList<>();
        String author = generatorConfig.getAuthor();
        String commentDate = GeneratorDateUtil.getCommentDate();
        String servicePkgName = generatorConfig.getPackageInfo().getServicePkgName();
        generatorConfig.getTableNames().forEach(tableName -> {
            CommonServiceTemplateData commonServiceTemplateData = new CommonServiceTemplateData()
                .setClassName(GeneratorStringUtil.tableNameToClassName(tableName, MvcLevel.SERVICE))
                .setPkgName(servicePkgName)
                .setAuthor(author)
                .setCreateDate(commentDate);
            serviceTemplateDataList.add(commonServiceTemplateData);
        });
        return serviceTemplateDataList;
    }
}
