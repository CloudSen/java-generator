package com.yangyunsen.generator.java.converter;

import com.yangyunsen.generator.java.common.model.dto.DatabaseInfo;
import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.common.model.dto.PackageInfo;
import com.yangyunsen.generator.java.common.model.enums.JdbcDriverPkgName;
import com.yangyunsen.generator.java.common.model.enums.JdbcUrlPrefix;
import com.yangyunsen.generator.java.common.model.enums.Mode;
import com.yangyunsen.generator.java.converter.model.*;
import com.yangyunsen.generator.java.converter.model.jpa.EntityField;
import com.yangyunsen.generator.java.dbloader.DbLoader;
import com.yangyunsen.generator.java.dbloader.oracle.OracleColumnInfo;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author clouds3n
 * @date 2021-09-29
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConverterTest {

    public static final PackageInfo PACKAGE_INFO = new PackageInfo()
        .setEntityPkgName("com.yangyunsen.test")
        .setControllerPkgName("com.yangyunsen.test.controller")
        .setServicePkgName("com.yangyunsen.test.service")
        .setServiceImplPkgName("com.yangyunsen.test.service.impl")
        .setRepoPkgName("com.yangyunsen.test.repository");
    public static final DatabaseInfo DATABASE_INFO = new DatabaseInfo()
        .setUrl(JdbcUrlPrefix.ORACLE.getPrefix() + "172.20.254.14:1521:orcl")
        .setUsername("CQDX_JXGLXX")
        .setPasswd("cquisse")
        .setDriverPkgName(JdbcDriverPkgName.ORACLE);
    public static final GeneratorConfig GENERATOR_CONFIG = GeneratorConfig.builder()
        .author("CloudS3n")
        .mode(Mode.JPA)
        .tableNames(Set.of("TEST_GENERATOR"))
        .databaseInfo(DATABASE_INFO)
        .packageInfo(PACKAGE_INFO)
        .build();

    @Order(1)
    @Test
    @DisplayName("??????????????????????????????")
    void convertEntityOneTable() {
        Map<String, List<OracleColumnInfo>> tableColumnsMap = DbLoader.getColumnInfo(GENERATOR_CONFIG);
        assertDoesNotThrow(() -> {
            List<EntityTemplateData> entityTemplateData = Converter.convertEntity(GENERATOR_CONFIG, tableColumnsMap);
            assertNotNull(entityTemplateData);
            // ?????????????????????
            assertEquals(1, entityTemplateData.size());
            entityTemplateData.forEach(data -> {
                assertEquals("TestGeneratorEntity", data.getClassName());
                assertEquals("com.yangyunsen.test", data.getPkgName());
                assertEquals("TEST_GENERATOR", data.getTableName());
                assertEquals(3, data.getFields().size());
                // ????????????
                assertEquals(1, data.getFields().stream().filter(entityField -> BooleanUtils.isTrue(entityField.getPkFlg())).count());
                EntityField pkField = data.getFields().stream().filter(entityField -> BooleanUtils.isTrue(entityField.getPkFlg())).findFirst().orElse(null);
                assertNotNull(pkField);
                assertEquals("UNIQUE_ID", pkField.getDbName());
                assertTrue(pkField.getPkFlg());
                assertEquals("uniqueId", pkField.getJavaName());
                assertEquals("String", pkField.getJavaType());
                // ??????????????????
                data.getFields().stream().filter(entityField -> BooleanUtils.isNotTrue(entityField.getPkFlg()))
                    .forEach(field -> {
                        assertNotNull(field);
                        assertNull(field.getPkFlg());
                        assertNotNull(field.getDbName());
                        assertNotNull(field.getJavaName());
                        assertNotNull(field.getJavaType());
                        if (StringUtils.contains(field.getDbName(), "NAME")) {
                            assertEquals("NAME", field.getDbName());
                            assertEquals("name", field.getJavaName());
                            assertEquals("String", field.getJavaType());
                        } else {
                            assertEquals("AGE", field.getDbName());
                            assertEquals("age", field.getJavaName());
                            assertEquals("Long", field.getJavaType());
                        }
                    });
            });
        });
    }

    @Order(2)
    @Test
    @DisplayName("??????????????????????????????")
    void convertEntityTwoTable() {
        GENERATOR_CONFIG.setTableNames(Set.of("TEST_GENERATOR", "TEST_GENERATOR2"));
        Map<String, List<OracleColumnInfo>> tableColumnsMap = DbLoader.getColumnInfo(GENERATOR_CONFIG);
        assertDoesNotThrow(() -> {
            List<EntityTemplateData> entityTemplateData = Converter.convertEntity(GENERATOR_CONFIG, tableColumnsMap);
            assertNotNull(entityTemplateData);
            // ????????????Entity
            assertEquals(2, entityTemplateData.size());
            entityTemplateData.forEach(data -> {
                if (!StringUtils.contains(data.getClassName(), "2")) {
                    assertEquals("TEST_GENERATOR", data.getTableName());
                    assertEquals("TestGeneratorEntity", data.getClassName());
                } else {
                    assertEquals("TEST_GENERATOR2", data.getTableName());
                    assertEquals("TestGenerator2Entity", data.getClassName());
                }
                assertEquals("com.yangyunsen.test", data.getPkgName());
                assertEquals(3, data.getFields().size());
                // ????????????
                assertEquals(1, data.getFields().stream().filter(entityField -> BooleanUtils.isTrue(entityField.getPkFlg())).count());
                EntityField pkField = data.getFields().stream().filter(entityField -> BooleanUtils.isTrue(entityField.getPkFlg())).findFirst().orElse(null);
                assertNotNull(pkField);
                assertEquals("UNIQUE_ID", pkField.getDbName());
                assertTrue(pkField.getPkFlg());
                assertEquals("uniqueId", pkField.getJavaName());
                assertEquals("String", pkField.getJavaType());
                // ??????????????????
                data.getFields().stream().filter(entityField -> BooleanUtils.isNotTrue(entityField.getPkFlg()))
                    .forEach(field -> {
                        assertNotNull(field);
                        assertNull(field.getPkFlg());
                        assertNotNull(field.getDbName());
                        assertNotNull(field.getJavaName());
                        assertNotNull(field.getJavaType());
                        if (StringUtils.contains(field.getDbName(), "NAME")) {
                            assertEquals("NAME", field.getDbName());
                            assertEquals("name", field.getJavaName());
                            assertEquals("String", field.getJavaType());
                        } else {
                            assertEquals("AGE", field.getDbName());
                            assertEquals("age", field.getJavaName());
                            assertEquals("Long", field.getJavaType());
                        }
                    });
            });
        });
    }

    @Order(3)
    @Test
    @DisplayName("?????????????????????????????????")
    void convertControllerTwoTable() {
        GENERATOR_CONFIG.setTableNames(Set.of("TEST_GENERATOR", "TEST_GENERATOR2"));
        assertDoesNotThrow(() -> {
            List<ControllerTemplateData> controllerTemplateData = Converter.convertController(GENERATOR_CONFIG);
            assertNotNull(controllerTemplateData);
            // ????????????controller
            assertEquals(2, controllerTemplateData.size());
            controllerTemplateData.forEach(data -> {
                if (!StringUtils.contains(data.getClassName(), "2")) {
                    assertEquals("test-generator", data.getControllerUrl());
                    assertEquals("TestGeneratorController", data.getClassName());
                    assertEquals("TestGeneratorService", data.getServiceClassName());
                    assertEquals("com.yangyunsen.test.service.TestGeneratorService", data.getImportServicePkgName());
                } else {
                    assertEquals("test-generator2", data.getControllerUrl());
                    assertEquals("TestGenerator2Controller", data.getClassName());
                    assertEquals("TestGenerator2Service", data.getServiceClassName());
                    assertEquals("com.yangyunsen.test.service.TestGenerator2Service", data.getImportServicePkgName());
                }
                assertEquals("com.yangyunsen.test.controller", data.getPkgName());
            });
        });
    }

    @Order(4)
    @Test
    @DisplayName("??????Service????????????????????????")
    void convertServiceTwoTable() {
        GENERATOR_CONFIG.setTableNames(Set.of("TEST_GENERATOR", "TEST_GENERATOR2"));
        assertDoesNotThrow(() -> {
            List<ServiceTemplateData> serviceTempData = Converter.convertService(GENERATOR_CONFIG);
            assertNotNull(serviceTempData);
            // ????????????service
            assertEquals(2, serviceTempData.size());
            serviceTempData.forEach(data -> {
                if (!StringUtils.contains(data.getClassName(), "2")) {
                    assertEquals("TestGeneratorService", data.getClassName());
                } else {
                    assertEquals("TestGenerator2Service", data.getClassName());
                }
                assertEquals("CloudS3n", data.getAuthor());
                assertEquals("com.yangyunsen.test.service", data.getPkgName());
            });
        });
    }

    @Order(5)
    @Test
    @DisplayName("??????ServiceImpl??????????????????")
    void convertServiceImplTwoTable() {
        GENERATOR_CONFIG.setTableNames(Set.of("TEST_GENERATOR", "TEST_GENERATOR2"));
        assertDoesNotThrow(() -> {
            List<ServiceImplTemplateData> serviceImplTempData = Converter.convertServiceImpl(GENERATOR_CONFIG);
            assertNotNull(serviceImplTempData);
            // ????????????service impl
            assertEquals(2, serviceImplTempData.size());
            serviceImplTempData.forEach(data -> {
                if (!StringUtils.contains(data.getClassName(), "2")) {
                    assertEquals("TestGeneratorServiceImpl", data.getClassName());
                } else {
                    assertEquals("TestGenerator2ServiceImpl", data.getClassName());
                }
                assertEquals("CloudS3n", data.getAuthor());
                assertEquals("com.yangyunsen.test.service.impl", data.getPkgName());
            });
        });
    }

    @Order(6)
    @Test
    @DisplayName("??????Repo????????????????????????")
    void convertRepoTwoTable() {
        GENERATOR_CONFIG.setTableNames(Set.of("TEST_GENERATOR", "TEST_GENERATOR2"));
        assertDoesNotThrow(() -> {
            Map<String, List<OracleColumnInfo>> columnInfo = DbLoader.getColumnInfo(GENERATOR_CONFIG);
            Map<String, String> tablePkTypeMap = Converter.convertEntity(GENERATOR_CONFIG, columnInfo).stream()
                .map(EntityTemplateData::getFields)
                .flatMap(Collection::stream)
                .filter(entityField -> BooleanUtils.isTrue(entityField.getPkFlg()))
                .collect(Collectors.toMap(EntityField::getTableName, EntityField::getJavaType));
            List<RepoTemplateData> repoTemplateData = Converter.convertRepo(GENERATOR_CONFIG, tablePkTypeMap);
            assertNotNull(repoTemplateData);
            // ????????????Repo
            assertEquals(2, repoTemplateData.size());
            repoTemplateData.forEach(data -> {
                if (!StringUtils.contains(data.getClassName(), "2")) {
                    assertEquals("TestGeneratorRepository", data.getClassName());
                    assertEquals("com.yangyunsen.test.model.entity.TestGeneratorEntity", data.getEntityPkgName());
                    assertEquals("TestGeneratorEntity", data.getEntityClassName());
                    assertEquals("String", data.getPkJavaType());
                } else {
                    assertEquals("TestGenerator2Repository", data.getClassName());
                    assertEquals("com.yangyunsen.test.model.entity.TestGenerator2Entity", data.getEntityPkgName());
                    assertEquals("TestGenerator2Entity", data.getEntityClassName());
                    assertEquals("String", data.getPkJavaType());
                }
                assertEquals("CloudS3n", data.getAuthor());
                assertEquals("com.yangyunsen.test.repository", data.getPkgName());
            });
        });
    }
}
