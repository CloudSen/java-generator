package com.yangyunsen.generator.java.filewriter;

import com.yangyunsen.generator.java.common.model.dto.DatabaseInfo;
import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.common.model.dto.PackageInfo;
import com.yangyunsen.generator.java.common.model.enums.JdbcDriverPkgName;
import com.yangyunsen.generator.java.common.model.enums.JdbcUrlPrefix;
import com.yangyunsen.generator.java.common.model.enums.Mode;
import com.yangyunsen.generator.java.converter.EntityTemplateData;
import com.yangyunsen.generator.java.converter.jpa.model.EntityField;
import com.yangyunsen.generator.java.converter.jpa.model.JpaEntityTemplateData;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author CloudS3n
 * @date 2021-09-30 14:25
 */
class EntityFileWriterTest {

    @Test
    void writeEntityToDisk() {
        DatabaseInfo databaseInfo = new DatabaseInfo()
            .setUrl(JdbcUrlPrefix.ORACLE.getPrefix() + "172.20.254.14:1521:orcl")
            .setUsername("CQDX_JXGLXX")
            .setPasswd("cquisse")
            .setDriverPkgName(JdbcDriverPkgName.ORACLE);
        PackageInfo packageInfo = new PackageInfo()
            .setEntityPkgName("com.yangyunsen.generator.java.entity");
        GeneratorConfig generatorConfig = new GeneratorConfig()
            .setAuthor("CloudS3n")
            .setMode(Mode.JPA)
            .setTableNames(Set.of("TEST_GENERATOR"))
            .setDatabaseInfo(databaseInfo)
            .setPackageInfo(packageInfo);
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
            EntityFileWriter.writeEntityToDisk(generatorConfig, entityTemplateDataList);
        });
    }
}
