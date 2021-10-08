package com.yangyunsen.generator.java.filewriter;

import com.yangyunsen.generator.java.common.model.dto.DatabaseInfo;
import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.common.model.dto.PackageInfo;
import com.yangyunsen.generator.java.common.model.enums.JdbcDriverPkgName;
import com.yangyunsen.generator.java.common.model.enums.JdbcUrlPrefix;
import com.yangyunsen.generator.java.common.model.enums.Mode;
import com.yangyunsen.generator.java.common.model.statics.CommonStatic;
import com.yangyunsen.generator.java.converter.model.EntityTemplateData;
import com.yangyunsen.generator.java.converter.model.jpa.EntityField;
import com.yangyunsen.generator.java.converter.model.jpa.JpaEntityTemplateData;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author CloudS3n
 * @date 2021-09-30 14:25
 */
@Slf4j
class EntityFileWriterTest implements FileCleaner {

    private static final Set<String> FILE_PATHS = new HashSet<>();

    @Test
    void writeEntityToDisk() {
        DatabaseInfo databaseInfo = new DatabaseInfo()
            .setUrl(JdbcUrlPrefix.ORACLE.getPrefix() + "172.20.254.14:1521:orcl")
            .setUsername("CQDX_JXGLXX")
            .setPasswd("cquisse")
            .setDriverPkgName(JdbcDriverPkgName.ORACLE);
        PackageInfo packageInfo = new PackageInfo()
            .setEntityPkgName("com.yangyunsen.generator.java.entity");
        GeneratorConfig generatorConfig = GeneratorConfig.builder()
            .author("CloudS3n")
            .mode(Mode.JPA)
            .tableNames(Set.of("TEST_GENERATOR"))
            .databaseInfo(databaseInfo)
            .packageInfo(packageInfo)
            .build();
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
            EntityFileWriter.writeEntityToDisk(generatorConfig, entityTemplateDataList);
        });
    }

    @AfterAll
    static void cleanResource() {
        FILE_PATHS.forEach(path -> log.info("删除目录和内容: {}", path));
        FileCleaner.deleteFiles(FILE_PATHS);
    }
}
