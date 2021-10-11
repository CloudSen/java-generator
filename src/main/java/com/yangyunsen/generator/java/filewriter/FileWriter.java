package com.yangyunsen.generator.java.filewriter;

import com.yangyunsen.generator.java.common.FreeMarkerManager;
import com.yangyunsen.generator.java.common.mapping.DefaultTemplateMapping;
import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.common.model.dto.PackageInfo;
import com.yangyunsen.generator.java.common.model.enums.MvcLevel;
import com.yangyunsen.generator.java.common.model.statics.CommonStatic;
import com.yangyunsen.generator.java.converter.model.CommonTemplateData;
import com.yangyunsen.generator.java.util.GeneratorStringUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author CloudS3n
 * @date 2021-10-09 10:32
 */
public class FileWriter {

    public static void writeFileToDisk(GeneratorConfig generatorConfig, List<Object> tempDataList, MvcLevel mvcLevel)
        throws IOException, TemplateException {

        Configuration fmc = FreeMarkerManager.getConfig();
        PackageInfo packageInfo = generatorConfig.getPackageInfo();
        String templateName = DefaultTemplateMapping.getTemplateName(generatorConfig.getMode(), mvcLevel);
        Template template = fmc.getTemplate(templateName);
        String pathStr = GeneratorStringUtil.getPathStrByMvcLevel(packageInfo, mvcLevel);
        Path path = Paths.get(URI.create(CommonStatic.FILE_SCHEMA + pathStr));
        String filePathStr;
        Path filePath;
        boolean pathExists = Files.exists(path);
        if (!pathExists) {
            System.out.println("创建路径: " + pathStr);
            Files.createDirectories(path);
        }
        for (Object templateData : tempDataList) {
            filePathStr = pathStr + ((CommonTemplateData) templateData).getClassName() + CommonStatic.JAVA_FILE_SUFFIX;
            filePath = Paths.get(URI.create(CommonStatic.FILE_SCHEMA + filePathStr));
            boolean fileExists = Files.exists(filePath);
            if (fileExists) {
                System.out.println("文件已存在: " + filePathStr);
                return;
            }
            System.out.println("创建文件: " + filePathStr);
            template.process(templateData, Files.newBufferedWriter(filePath));
        }
    }
}
