package com.yangyunsen.generator.java.filewriter;

import cn.hutool.system.SystemUtil;
import com.yangyunsen.generator.java.common.FreeMarkerManager;
import com.yangyunsen.generator.java.common.mapping.JpaTemplateMapping;
import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.common.model.dto.PackageInfo;
import com.yangyunsen.generator.java.common.model.enums.MvcLevel;
import com.yangyunsen.generator.java.common.model.statics.CommonStatic;
import com.yangyunsen.generator.java.converter.EntityTemplateData;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * 文件生成器
 *
 * @author CloudS3n
 * @date 2021-09-30 09:56
 */
@Slf4j
public class FileWriter {

    public static void writeEntityToDisk(GeneratorConfig generatorConfig, List<EntityTemplateData> tempDataList)
        throws IOException, TemplateException {

        Configuration fmc = FreeMarkerManager.getConfig();
        PackageInfo packageInfo = generatorConfig.getPackageInfo();
        String entityTemplateName = JpaTemplateMapping.getTemplateName(generatorConfig.getMode(), MvcLevel.ENTITY);
        Template template = fmc.getTemplate(entityTemplateName);
        String entityPathStr = parsePkgNameToPath(packageInfo.getEntityPkgName());
        Path entityPath = Paths.get(URI.create(entityPathStr));
        boolean pathExists = Files.exists(entityPath);
        if (!pathExists) {

            Files.createDirectories(entityPath);
        }
        for (EntityTemplateData templateData : tempDataList) {
            template.process(templateData, new OutputStreamWriter(System.out));
        }
    }

    public static String parsePkgNameToPath(String pkgName) {
        return SystemUtil.getOsInfo().isWindows()?
            CommonStatic.JAVA_PATH + pkgName.replaceAll("\\.", "\\\\") + File.separator
            : CommonStatic.JAVA_PATH + pkgName.replaceAll("\\.", File.separator) + File.separator;
    }
}
