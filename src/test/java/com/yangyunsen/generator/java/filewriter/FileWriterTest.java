package com.yangyunsen.generator.java.filewriter;

import com.yangyunsen.generator.java.common.model.dto.DatabaseInfo;
import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.common.model.dto.PackageInfo;
import com.yangyunsen.generator.java.common.model.enums.JdbcDriverPkgName;
import com.yangyunsen.generator.java.common.model.enums.JdbcUrlPrefix;
import com.yangyunsen.generator.java.common.model.enums.Mode;
import com.yangyunsen.generator.java.common.model.enums.MvcLevel;
import com.yangyunsen.generator.java.common.model.statics.CommonStatic;
import com.yangyunsen.generator.java.converter.model.ControllerTemplateData;
import com.yangyunsen.generator.java.converter.model.EntityTemplateData;
import com.yangyunsen.generator.java.converter.model.RepoTemplateData;
import com.yangyunsen.generator.java.converter.model.ServiceImplTemplateData;
import com.yangyunsen.generator.java.converter.model.common.CommonControllerTemplateData;
import com.yangyunsen.generator.java.converter.model.common.CommonServiceTemplateData;
import com.yangyunsen.generator.java.converter.model.jpa.EntityField;
import com.yangyunsen.generator.java.converter.model.jpa.JpaEntityTemplateData;
import com.yangyunsen.generator.java.converter.model.jpa.JpaRepoTemplateData;
import com.yangyunsen.generator.java.converter.model.jpa.JpaServiceImplTemplateData;
import com.yangyunsen.generator.java.util.GeneratorDateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author CloudS3n
 * @date 2021-09-30 14:25
 */
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FileWriterTest implements FileCleaner {

    private static final Set<String> FILE_PATHS = new HashSet<>();
    private static final DatabaseInfo DATABASE_INFO = new DatabaseInfo()
        .setUrl(JdbcUrlPrefix.ORACLE.getPrefix() + "172.20.254.14:1521:orcl")
        .setUsername("CQDX_JXGLXX")
        .setPasswd("cquisse")
        .setDriverPkgName(JdbcDriverPkgName.ORACLE);
    private static final PackageInfo PACKAGE_INFO = new PackageInfo()
        .setEntityPkgName("com.yangyunsen.generator.java.entity")
        .setControllerPkgName("com.yangyunsen.generator.java.controller")
        .setServicePkgName("com.yangyunsen.generator.java.service")
        .setServiceImplPkgName("com.yangyunsen.generator.java.service.impl")
        .setRepoPkgName("com.yangyunsen.generator.java.repository");
    private static final GeneratorConfig GENERATOR_CONFIG = GeneratorConfig.builder()
        .author("CloudS3n")
        .mode(Mode.JPA)
        .tableNames(Set.of("TEST_GENERATOR"))
        .databaseInfo(DATABASE_INFO)
        .packageInfo(PACKAGE_INFO)
        .build();

    @Order(1)
    @Test
    @DisplayName("写ORM Entity文件")
    void writeEntityToDisk() {
        assertDoesNotThrow(() -> {
            List<EntityTemplateData> entityTemplateDataList = Arrays.asList(
                new JpaEntityTemplateData().setAuthor("clouds3n")
                    .setClassName("TestGeneratorEntity")
                    .setPkgName("com.yangyunsen.generator.java.entity")
                    .setTableName("TEST_GENERATOR")
                    .setCreateDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .setFields(Arrays.asList(
                        new EntityField().setPkFlg(true).setDbName("UNIQUE_ID").setJavaName("id").setJavaType("String"),
                        new EntityField().setPkFlg(false).setDbName("NAME").setJavaName("name").setJavaType("String"),
                        new EntityField().setPkFlg(null).setDbName("AGE").setJavaName("age").setJavaType("Integer")
                    )),
                new JpaEntityTemplateData().setAuthor("clouds3n")
                    .setClassName("TestGenerator2Entity")
                    .setPkgName("com.yangyunsen.generator.java.entity")
                    .setTableName("TEST_GENERATOR2")
                    .setCreateDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .setFields(Arrays.asList(
                        new EntityField().setPkFlg(true).setDbName("UNIQUE_ID").setJavaName("id").setJavaType("String"),
                        new EntityField().setPkFlg(false).setDbName("NAME").setJavaName("name").setJavaType("String"),
                        new EntityField().setPkFlg(null).setDbName("AGE").setJavaName("age").setJavaType("Integer")
                    ))
            );
            entityTemplateDataList.stream().map(EntityTemplateData::getPkgName)
                .map(pkgName -> pkgName.replaceAll("\\.", CommonStatic.SLASH) + CommonStatic.SLASH)
                .forEach(FILE_PATHS::add);
            FileWriter.writeFileToDisk(GENERATOR_CONFIG, new ArrayList<>(entityTemplateDataList), MvcLevel.ENTITY);
        });
    }

    @Order(2)
    @Test
    @DisplayName("写Controller文件")
    void writeControllerToDisk() {
        assertDoesNotThrow(() -> {
            List<ControllerTemplateData> ctrlTempDataList = Arrays.asList(
                new CommonControllerTemplateData().setAuthor("clouds3n")
                    .setClassName("TestGeneratorController")
                    .setServiceClassName("TestGeneratorService")
                    .setPkgName("com.yangyunsen.generator.java.controller")
                    .setCreateDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .setControllerUrl("test-generator")
                    .setImportServicePkgName("com.yangyunsen.generator.java.service.TestGeneratorService"),
                new CommonControllerTemplateData().setAuthor("clouds3n")
                    .setClassName("TestGenerator2Controller")
                    .setServiceClassName("TestGenerator2Service")
                    .setPkgName("com.yangyunsen.generator.java.controller")
                    .setCreateDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .setControllerUrl("test-generator2")
                    .setImportServicePkgName("com.yangyunsen.generator.java.service.TestGenerator2Service")
            );
            ctrlTempDataList.stream().map(ControllerTemplateData::getPkgName)
                .map(pkgName -> pkgName.replaceAll("\\.", CommonStatic.SLASH) + CommonStatic.SLASH)
                .forEach(FILE_PATHS::add);
            FileWriter.writeFileToDisk(GENERATOR_CONFIG, new ArrayList<>(ctrlTempDataList), MvcLevel.CONTROLLER);
        });
    }

    @Order(3)
    @Test
    @DisplayName("写Service文件")
    void writeServiceToDisk() {
        assertDoesNotThrow(() -> {
            List<CommonServiceTemplateData> serviceTempDataList = Arrays.asList(
                new CommonServiceTemplateData().setAuthor("clouds3n")
                    .setClassName("TestGeneratorService")
                    .setPkgName("com.yangyunsen.generator.java.service")
                    .setCreateDate(GeneratorDateUtil.getCommentDate()),
                new CommonServiceTemplateData().setAuthor("clouds3n")
                    .setClassName("TestGenerator2Service")
                    .setPkgName("com.yangyunsen.generator.java.service")
                    .setCreateDate(GeneratorDateUtil.getCommentDate())
            );
            serviceTempDataList.stream().map(CommonServiceTemplateData::getPkgName)
                .map(pkgName -> pkgName.replaceAll("\\.", CommonStatic.SLASH) + CommonStatic.SLASH)
                .forEach(FILE_PATHS::add);
            FileWriter.writeFileToDisk(GENERATOR_CONFIG, new ArrayList<>(serviceTempDataList), MvcLevel.SERVICE);
        });
    }

    @Order(3)
    @Test
    @DisplayName("写ServiceImpl文件")
    void writeServiceImplToDisk() {
        assertDoesNotThrow(() -> {
            List<ServiceImplTemplateData> serviceTempDataList = Arrays.asList(
                new JpaServiceImplTemplateData().setAuthor("clouds3n")
                    .setPkgName("com.yangyunsen.generator.java.service.impl")
                    .setServicePkgName("com.yangyunsen.generator.java.service.TestGeneratorService")
                    .setRepoPkgName("com.yangyunsen.generator.java.service.TestGeneratorRepo")
                    .setClassName("TestGeneratorServiceImpl")
                    .setServiceClassName("TestGeneratorService")
                    .setRepoClassName("TestGeneratorRepository")
                    .setCreateDate(GeneratorDateUtil.getCommentDate()),
                new JpaServiceImplTemplateData().setAuthor("clouds3n")
                    .setPkgName("com.yangyunsen.generator.java.service.impl")
                    .setServicePkgName("com.yangyunsen.generator.java.service.TestGeneratorService")
                    .setRepoPkgName("com.yangyunsen.generator.java.service.TestGeneratorRepo")
                    .setClassName("TestGenerator2ServiceImpl")
                    .setServiceClassName("TestGenerator2Service")
                    .setRepoClassName("TestGenerator2Repository")
                    .setCreateDate(GeneratorDateUtil.getCommentDate())
            );
            serviceTempDataList.stream().map(ServiceImplTemplateData::getPkgName)
                .map(pkgName -> pkgName.replaceAll("\\.", CommonStatic.SLASH) + CommonStatic.SLASH)
                .forEach(FILE_PATHS::add);
            FileWriter.writeFileToDisk(GENERATOR_CONFIG, new ArrayList<>(serviceTempDataList), MvcLevel.SERVICE_IMPL);
        });
    }

    @Order(5)
    @Test
    @DisplayName("写JPA Repo文件")
    void writeRepoToDisk() {
        assertDoesNotThrow(() -> {
            List<RepoTemplateData> repoTempDataList = Arrays.asList(
                new JpaRepoTemplateData()
                    .setAuthor("clouds3n")
                    .setCreateDate(GeneratorDateUtil.getCommentDate())
                    .setClassName("TestGeneratorRepository")
                    .setPkJavaType("String")
                    .setPkgName("com.yangyunsen.generator.java.repository")
                    .setEntityPkgName("com.yangyunsen.generator.java.model.entity.TestGeneratorEntity")
                    .setEntityClassName("TestGeneratorEntity"),
                new JpaRepoTemplateData()
                    .setAuthor("clouds3n")
                    .setCreateDate(GeneratorDateUtil.getCommentDate())
                    .setClassName("TestGenerator2Repository")
                    .setPkJavaType("String")
                    .setPkgName("com.yangyunsen.generator.java.repository")
                    .setEntityPkgName("com.yangyunsen.generator.java.model.entity.TestGenerator2Entity")
                    .setEntityClassName("TestGenerator2Entity")
            );
            repoTempDataList.stream().map(RepoTemplateData::getPkgName)
                .map(pkgName -> pkgName.replaceAll("\\.", CommonStatic.SLASH) + CommonStatic.SLASH)
                .forEach(FILE_PATHS::add);
            FileWriter.writeFileToDisk(GENERATOR_CONFIG, new ArrayList<>(repoTempDataList), MvcLevel.REPO);
        });
    }

    @AfterAll
    static void cleanResource() {
        FILE_PATHS.forEach(path -> log.info("删除目录和内容: {}", path));
        FileCleaner.deleteFiles(FILE_PATHS);
    }
}
