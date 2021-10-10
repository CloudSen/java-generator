package com.yangyunsen.generator.java.generator;

import com.yangyunsen.generator.java.common.GeneratorException;
import com.yangyunsen.generator.java.common.model.dto.EnableSwitch;
import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.common.model.enums.MvcLevel;
import com.yangyunsen.generator.java.converter.Converter;
import com.yangyunsen.generator.java.converter.model.*;
import com.yangyunsen.generator.java.converter.model.jpa.EntityField;
import com.yangyunsen.generator.java.dbloader.DbLoader;
import com.yangyunsen.generator.java.dbloader.oracle.OracleColumnInfo;
import com.yangyunsen.generator.java.filewriter.FileWriter;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * JPA生成器入口
 *
 * @author clouds3n
 * @date 2021-09-29
 */
@Slf4j
@RequiredArgsConstructor
public class JpaGenerator implements Generator {

    private final GeneratorConfig config;


    @Override
    public void generate() {
        try {
            EnableSwitch enableSwitch = config.getEnableSwitch();
            if (BooleanUtils.isTrue(enableSwitch.getGenerateEntity())) {
                generateEntity();
            }
            if (BooleanUtils.isTrue(enableSwitch.getGenerateDTO())) {
                generateDTO();
            }
            if (BooleanUtils.isTrue(enableSwitch.getGenerateRepo())) {
                generateRepo();
            }
            if (BooleanUtils.isTrue(enableSwitch.getGenerateCustomRepo())) {
                generateCustomRepo();
            }
            if (BooleanUtils.isTrue(enableSwitch.getGenerateCustomRepoImpl())) {
                generateCustomRepoImpl();
            }
            if (BooleanUtils.isTrue(enableSwitch.getGenerateService())) {
                generateService();
            }
            if (BooleanUtils.isTrue(enableSwitch.getGenerateServiceImpl())) {
                generateServiceImpl();
            }
            if (BooleanUtils.isTrue(enableSwitch.getGenerateController())) {
                generateController();
            }
        } catch (Exception e) {
            throw new GeneratorException(e);
        }
    }

    @Override
    public void generateEntity() throws TemplateException, IOException {
        Map<String, List<OracleColumnInfo>> columnInfo = DbLoader.getColumnInfo(config);
        List<EntityTemplateData> entityTemplateData = Converter.convertEntity(config, columnInfo);
        FileWriter.writeFileToDisk(config, Collections.singletonList(entityTemplateData), MvcLevel.ENTITY);
    }

    @Override
    public void generateRepo() throws TemplateException, IOException {
        Map<String, List<OracleColumnInfo>> columnInfo = DbLoader.getColumnInfo(config);
        List<EntityTemplateData> entityTemplateData = Converter.convertEntity(config, columnInfo);
        Map<String, String> tablePkTypeMap = entityTemplateData.stream()
            .map(EntityTemplateData::getFields)
            .flatMap(Collection::stream)
            .filter(entityField -> BooleanUtils.isTrue(entityField.getPkFlg()))
            .collect(Collectors.toMap(EntityField::getTableName, EntityField::getJavaType));
        List<RepoTemplateData> repoTemplateData = Converter.convertRepo(config, tablePkTypeMap);
        FileWriter.writeFileToDisk(config, Collections.singletonList(repoTemplateData), MvcLevel.REPO);
    }

    @Override
    public void generateService() throws TemplateException, IOException {
        List<ServiceTemplateData> serviceTemplateData = Converter.convertService(config);
        FileWriter.writeFileToDisk(config, Collections.singletonList(serviceTemplateData), MvcLevel.SERVICE);
    }

    @Override
    public void generateServiceImpl() throws TemplateException, IOException {
        List<ServiceImplTemplateData> serviceImplTemplateData = Converter.convertServiceImpl(config);
        FileWriter.writeFileToDisk(config, Collections.singletonList(serviceImplTemplateData), MvcLevel.SERVICE_IMPL);
    }

    @Override
    public void generateController() throws TemplateException, IOException {
        List<ControllerTemplateData> controllerTemplateData = Converter.convertController(config);
        FileWriter.writeFileToDisk(config, Collections.singletonList(controllerTemplateData), MvcLevel.CONTROLLER);
    }
}
