package com.yangyunsen.generator.java.converter;

import com.yangyunsen.generator.java.common.model.dto.DatabaseInfo;
import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.common.model.dto.PackageInfo;
import com.yangyunsen.generator.java.common.model.enums.JdbcDriverPkgName;
import com.yangyunsen.generator.java.common.model.enums.JdbcUrlPrefix;
import com.yangyunsen.generator.java.common.model.enums.Mode;
import com.yangyunsen.generator.java.converter.jpa.model.EntityField;
import com.yangyunsen.generator.java.dbloader.DbLoader;
import com.yangyunsen.generator.java.dbloader.oracle.OracleColumnInfo;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author clouds3n
 * @date 2021-09-29
 */
class ConverterTest {

    @Test
    void convertOneTable() {
        PackageInfo packageInfo = new PackageInfo().setEntityPkgName("com.yangyunsen.test");
        DatabaseInfo databaseInfo = new DatabaseInfo()
            .setUrl(JdbcUrlPrefix.ORACLE.getPrefix() + "172.20.254.14:1521:orcl")
            .setUsername("CQDX_JXGLXX")
            .setPasswd("cquisse")
            .setDriverPkgName(JdbcDriverPkgName.ORACLE);
        GeneratorConfig generatorConfig = new GeneratorConfig()
            .setAuthor("CloudS3n")
            .setMode(Mode.JPA)
            .setTableNames(Set.of("TEST_GENERATOR"))
            .setDatabaseInfo(databaseInfo)
            .setPackageInfo(packageInfo);
        Map<String, List<OracleColumnInfo>> tableColumnsMap = DbLoader.getColumnInfo(generatorConfig);
        assertDoesNotThrow(() -> {
            List<EntityTemplateData> entityTemplateData = Converter.convert(generatorConfig, tableColumnsMap);
            assertNotNull(entityTemplateData);
            // 解析出了一张表
            assertEquals(1, entityTemplateData.size());
            entityTemplateData.forEach(data -> {
                assertEquals("TestGenerator", data.getClassName());
                assertEquals("com.yangyunsen.test", data.getPkgName());
                assertEquals("TEST_GENERATOR", data.getTableName());
                assertEquals(3, data.getFields().size());
                // 校验主键
                assertEquals(1, data.getFields().stream().filter(entityField -> BooleanUtils.isTrue(entityField.getPkFlg())).count());
                EntityField pkField = data.getFields().stream().filter(entityField -> BooleanUtils.isTrue(entityField.getPkFlg())).findFirst().orElse(null);
                assertNotNull(pkField);
                assertEquals("UNIQUE_ID", pkField.getDbName());
                assertTrue(pkField.getPkFlg());
                assertEquals("uniqueId", pkField.getJavaName());
                assertEquals("String", pkField.getJavaType());
                // 校验其他字段
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

    @Test
    void convertTwoTable() {
        PackageInfo packageInfo = new PackageInfo().setEntityPkgName("com.yangyunsen.test");
        DatabaseInfo databaseInfo = new DatabaseInfo()
            .setUrl(JdbcUrlPrefix.ORACLE.getPrefix() + "172.20.254.14:1521:orcl")
            .setUsername("CQDX_JXGLXX")
            .setPasswd("cquisse")
            .setDriverPkgName(JdbcDriverPkgName.ORACLE);
        GeneratorConfig generatorConfig = new GeneratorConfig()
            .setAuthor("CloudS3n")
            .setMode(Mode.JPA)
            .setTableNames(Set.of("TEST_GENERATOR", "TEST_GENERATOR2"))
            .setDatabaseInfo(databaseInfo)
            .setPackageInfo(packageInfo);
        Map<String, List<OracleColumnInfo>> tableColumnsMap = DbLoader.getColumnInfo(generatorConfig);
        assertDoesNotThrow(() -> {
            List<EntityTemplateData> entityTemplateData = Converter.convert(generatorConfig, tableColumnsMap);
            assertNotNull(entityTemplateData);
            // 解析出了2张表
            assertEquals(2, entityTemplateData.size());
            entityTemplateData.forEach(data -> {
                if (!StringUtils.contains(data.getClassName(), "2")) {
                    assertEquals("TEST_GENERATOR", data.getTableName());
                    assertEquals("TestGenerator", data.getClassName());
                } else {
                    assertEquals("TEST_GENERATOR2", data.getTableName());
                    assertEquals("TestGenerator2", data.getClassName());
                }
                assertEquals("com.yangyunsen.test", data.getPkgName());
                assertEquals(3, data.getFields().size());
                // 校验主键
                assertEquals(1, data.getFields().stream().filter(entityField -> BooleanUtils.isTrue(entityField.getPkFlg())).count());
                EntityField pkField = data.getFields().stream().filter(entityField -> BooleanUtils.isTrue(entityField.getPkFlg())).findFirst().orElse(null);
                assertNotNull(pkField);
                assertEquals("UNIQUE_ID", pkField.getDbName());
                assertTrue(pkField.getPkFlg());
                assertEquals("uniqueId", pkField.getJavaName());
                assertEquals("String", pkField.getJavaType());
                // 校验其他字段
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
}
