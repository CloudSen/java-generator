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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;

import java.util.*;
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
    }

    @Override
    public void generateEntity() {
        try {
            Map<String, List<OracleColumnInfo>> columnInfo = DbLoader.getColumnInfo(config);
            List<EntityTemplateData> entityTemplateData = Converter.convertEntity(config, columnInfo);
            FileWriter.writeFileToDisk(config, new ArrayList<>(entityTemplateData), MvcLevel.ENTITY);
        } catch (Exception e) {
            log.error("生成ORM实体异常");
            throw new GeneratorException(e);
        }
    }

    @Override
    public void generateRepo() {
        try {
            Map<String, List<OracleColumnInfo>> columnInfo = DbLoader.getColumnInfo(config);
            List<EntityTemplateData> entityTemplateData = Converter.convertEntity(config, columnInfo);
            Map<String, String> tablePkTypeMap = entityTemplateData.stream()
                .map(EntityTemplateData::getFields)
                .flatMap(Collection::stream)
                .filter(entityField -> BooleanUtils.isTrue(entityField.getPkFlg()))
                .collect(Collectors.toMap(EntityField::getTableName, EntityField::getJavaType));
            List<RepoTemplateData> repoTemplateData = Converter.convertRepo(config, tablePkTypeMap);
            FileWriter.writeFileToDisk(config, new ArrayList<>(repoTemplateData), MvcLevel.REPO);
        } catch (Exception e) {
            log.error("生成Repo接口异常");
            throw new GeneratorException(e);
        }
    }

    @Override
    public void generateService() {
        try {
            List<ServiceTemplateData> serviceTemplateData = Converter.convertService(config);
            FileWriter.writeFileToDisk(config, new ArrayList<>(serviceTemplateData), MvcLevel.SERVICE);
        } catch (Exception e) {
            log.error("生成Service接口异常");
            throw new GeneratorException(e);
        }
    }

    @Override
    public void generateServiceImpl() {
        try {
            List<ServiceImplTemplateData> serviceImplTemplateData = Converter.convertServiceImpl(config);
            FileWriter.writeFileToDisk(config, new ArrayList<>(serviceImplTemplateData), MvcLevel.SERVICE_IMPL);
        } catch (Exception e) {
            log.error("生成ServiceImpl异常");
            throw new GeneratorException(e);
        }
    }

    @Override
    public void generateController() {
        try {
            List<ControllerTemplateData> controllerTemplateData = Converter.convertController(config);
            FileWriter.writeFileToDisk(config, new ArrayList<>(controllerTemplateData), MvcLevel.CONTROLLER);
        } catch (Exception e) {
            log.error("生成Controller异常");
            throw new GeneratorException(e);
        }
    }
}
