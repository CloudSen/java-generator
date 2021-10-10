package com.yangyunsen.generator.java.converter.impl.common;

import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.common.model.enums.MvcLevel;
import com.yangyunsen.generator.java.converter.ControllerConverterStrategy;
import com.yangyunsen.generator.java.converter.model.ControllerTemplateData;
import com.yangyunsen.generator.java.converter.model.common.CommonControllerTemplateData;
import com.yangyunsen.generator.java.util.GeneratorDateUtil;
import com.yangyunsen.generator.java.util.GeneratorStringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用控制器模板数据转换
 *
 * @author CloudS3n
 * @date 2021-10-09 09:22
 */
public class CommonControllerConverter implements ControllerConverterStrategy {
    @Override
    public List<ControllerTemplateData> convert(GeneratorConfig generatorConfig) {
        List<ControllerTemplateData> controllerTemplateDataList = new ArrayList<>();
        String author = generatorConfig.getAuthor();
        String controllerPkgName = generatorConfig.getPackageInfo().getControllerPkgName();
        String parentPkgName = GeneratorStringUtil.getParentPkgName(controllerPkgName);
        String commentDate = GeneratorDateUtil.getCommentDate();
        generatorConfig.getTableNames().forEach(tableName -> {
            String importServicePkgName = GeneratorStringUtil.getBrotherMvcPkgName(parentPkgName, tableName, MvcLevel.SERVICE);
            String controllerUrl = GeneratorStringUtil.tableNameToControllerUrl(tableName);
            CommonControllerTemplateData templateData = new CommonControllerTemplateData()
                .setPkgName(controllerPkgName)
                .setImportServicePkgName(importServicePkgName)
                .setControllerUrl(controllerUrl)
                .setClassName(GeneratorStringUtil.tableNameToClassName(tableName, MvcLevel.CONTROLLER))
                .setServiceClassName(GeneratorStringUtil.tableNameToClassName(tableName, MvcLevel.SERVICE))
                .setAuthor(author)
                .setCreateDate(commentDate);
            controllerTemplateDataList.add(templateData);
        });
        return controllerTemplateDataList;
    }
}
