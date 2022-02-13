package com.yangyunsen.generator.java.generator;

import com.yangyunsen.generator.java.FileCleaner;
import com.yangyunsen.generator.java.common.model.dto.DatabaseInfo;
import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.common.model.dto.PackageInfo;
import com.yangyunsen.generator.java.common.model.enums.JdbcDriverPkgName;
import com.yangyunsen.generator.java.common.model.enums.JdbcUrlPrefix;
import com.yangyunsen.generator.java.common.model.enums.Mode;
import com.yangyunsen.generator.java.common.model.enums.MvcLevel;
import com.yangyunsen.generator.java.util.GeneratorStringUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author clouds3n
 * @date 2021-10-10
 */
class GeneratorTest implements FileCleaner {

    private static final Set<String> FILE_PATHS = new HashSet<>();

    private static final DatabaseInfo DATABASE_INFO = new DatabaseInfo()
        .setUrl(JdbcUrlPrefix.ORACLE.getPrefix() + "192.168.2.145:1521:orcl")
        .setUsername("CQDX_JXGLXX")
        .setPasswd("cquisse")
        .setDriverPkgName(JdbcDriverPkgName.ORACLE);

    private static final PackageInfo PACKAGE_INFO = new PackageInfo()
        .setEntityPkgName("com.yangyunsen.generator.java.model.entity")
        .setRepoPkgName("com.yangyunsen.generator.java.repository")
        .setServicePkgName("com.yangyunsen.generator.java.service")
        .setServiceImplPkgName("com.yangyunsen.generator.java.service.impl")
        .setControllerPkgName("com.yangyunsen.generator.java.controller");

    private static final GeneratorConfig GENERATOR_CONFIG = GeneratorConfig.builder()
        .author("CloudS3n")
        .mode(Mode.JPA)
        .tableNames(Set.of("TEST_GENERATOR"))
        .databaseInfo(DATABASE_INFO)
        .packageInfo(PACKAGE_INFO)
        .build();

    @AfterAll
    static void cleanResource() {
        FileCleaner.deleteFiles(FILE_PATHS);
    }

    @Test
    void generate() {
        FILE_PATHS.add(GeneratorStringUtil.getPathStrByMvcLevel(PACKAGE_INFO, MvcLevel.ENTITY));
        FILE_PATHS.add(GeneratorStringUtil.getPathStrByMvcLevel(PACKAGE_INFO, MvcLevel.REPO));
        FILE_PATHS.add(GeneratorStringUtil.getPathStrByMvcLevel(PACKAGE_INFO, MvcLevel.SERVICE));
        FILE_PATHS.add(GeneratorStringUtil.getPathStrByMvcLevel(PACKAGE_INFO, MvcLevel.SERVICE_IMPL));
        FILE_PATHS.add(GeneratorStringUtil.getPathStrByMvcLevel(PACKAGE_INFO, MvcLevel.CONTROLLER));
        Generator generator = new JpaGenerator(GENERATOR_CONFIG);
        Assertions.assertDoesNotThrow(generator::generate);
    }
}
