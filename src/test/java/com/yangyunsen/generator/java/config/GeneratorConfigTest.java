package com.yangyunsen.generator.java.config;

import com.yangyunsen.generator.java.common.GeneratorException;
import com.yangyunsen.generator.java.common.model.dto.*;
import com.yangyunsen.generator.java.common.model.enums.JdbcDriverPkgName;
import com.yangyunsen.generator.java.common.model.enums.JdbcUrlPrefix;
import com.yangyunsen.generator.java.common.model.enums.Mode;
import com.yangyunsen.generator.java.converter.impl.jpa.entity.JpaEntityConverterStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 全局配置测试类
 *
 * @author CloudS3n
 * @date 2021-10-08 22:50
 */
public class GeneratorConfigTest {

    @Order(1)
    @Test
    @DisplayName("非交互式-JPA默认全局配置")
    public void testDefaultConfigNonInteractive() {
        assertDoesNotThrow(() -> {
            PackageInfo packageInfo = new PackageInfo().setEntityPkgName("com.yangyunsen.generator.java.entity");
            DatabaseInfo databaseInfo = new DatabaseInfo()
                .setUrl(JdbcUrlPrefix.ORACLE.getPrefix() + "172.20.254.14:1521:orcl")
                .setUsername("CQDX_JXGLXX")
                .setPasswd("cquisse")
                .setDriverPkgName(JdbcDriverPkgName.ORACLE);
            GeneratorConfig config = GeneratorConfig.builder()
                .author("CloudS3n")
                .mode(Mode.JPA)
                .tableNames(Set.of("TEST_GENERATOR", "TEST_GENERATOR2"))
                .databaseInfo(databaseInfo)
                .packageInfo(packageInfo)
                .build();
            commonAssert(config);
        });
    }

    @Order(2)
    @Test
    @DisplayName("交互式-JPA默认全局配置")
    public void testDefaultConfigInteractive() {
        assertDoesNotThrow(() -> {
            PackageInfo packageInfo = new PackageInfo().setEntityPkgName("com.yangyunsen.generator.java.entity");
            DatabaseInfo databaseInfo = new DatabaseInfo()
                .setUrl(JdbcUrlPrefix.ORACLE.getPrefix() + "172.20.254.14:1521:orcl")
                .setUsername("CQDX_JXGLXX")
                .setPasswd("cquisse")
                .setDriverPkgName(JdbcDriverPkgName.ORACLE);
            System.setIn(new ByteArrayInputStream("TEST_GENERATOR,TEST_GENERATOR2".getBytes()));
            GeneratorConfig config = GeneratorConfig.builder()
                .author("CloudS3n")
                .mode(Mode.JPA)
                .databaseInfo(databaseInfo)
                .packageInfo(packageInfo)
                .build();
            commonAssert(config);
        });
    }

    @Order(3)
    @Test
    @DisplayName("不支持的模式")
    public void testNotSupportedMode() {
        assertThrows(GeneratorException.class, () -> {
            PackageInfo packageInfo = new PackageInfo().setEntityPkgName("com.yangyunsen.generator.java.entity");
            DatabaseInfo databaseInfo = new DatabaseInfo()
                .setUrl(JdbcUrlPrefix.ORACLE.getPrefix() + "172.20.254.14:1521:orcl")
                .setUsername("CQDX_JXGLXX")
                .setPasswd("cquisse")
                .setDriverPkgName(JdbcDriverPkgName.ORACLE);
            GeneratorConfig config = GeneratorConfig.builder()
                .author("CloudS3n")
                .mode(Mode.MP)
                .databaseInfo(databaseInfo)
                .packageInfo(packageInfo)
                .tableNames(Set.of("TEST_GENERATOR", "TEST_GENERATOR2"))
                .build();
        });
    }

    @Order(4)
    @Test
    @DisplayName("自定义开关")
    public void testCustomSwitch() {
        assertDoesNotThrow(() -> {
            PackageInfo packageInfo = new PackageInfo().setEntityPkgName("com.yangyunsen.generator.java.entity");
            DatabaseInfo databaseInfo = new DatabaseInfo()
                .setUrl(JdbcUrlPrefix.ORACLE.getPrefix() + "172.20.254.14:1521:orcl")
                .setUsername("CQDX_JXGLXX")
                .setPasswd("cquisse")
                .setDriverPkgName(JdbcDriverPkgName.ORACLE);
            EnableSwitch customEnableSwitch = new EnableSwitch().setGenerateOpenApi3(true);
            GeneratorConfig config = GeneratorConfig.builder()
                .author("CloudS3n")
                .mode(Mode.JPA)
                .tableNames(Set.of("TEST_GENERATOR", "TEST_GENERATOR2"))
                .databaseInfo(databaseInfo)
                .packageInfo(packageInfo)
                .enableSwitch(customEnableSwitch)
                .build();
            EnableSwitch enableSwitch = config.getEnableSwitch();
            assertNotNull(enableSwitch);
            assertTrue(enableSwitch.getGenerateEntity());
            assertTrue(enableSwitch.getGenerateDTO());
            assertTrue(enableSwitch.getGenerateRepo());
            assertTrue(enableSwitch.getGenerateCustomRepo());
            assertTrue(enableSwitch.getGenerateService());
            assertTrue(enableSwitch.getGenerateServiceImpl());
            assertTrue(enableSwitch.getGenerateController());
            assertTrue(enableSwitch.getGenerateOpenApi3());
        });
    }

    private void commonAssert(GeneratorConfig config) {
        EnableSwitch enableSwitch = config.getEnableSwitch();
        ComponentInfo componentInfo = config.getComponentInfo();
        assertEquals("CloudS3n", config.getAuthor());
        assertEquals(Mode.JPA, config.getMode());
        assertNotNull(config.getTableNames());
        assertNotNull(config.getDatabaseInfo());
        assertNotNull(config.getPackageInfo());
        assertNotNull(componentInfo);
        assertNotNull(enableSwitch);
        assertNotNull(componentInfo.getConverterComponent());
        assertNotNull(componentInfo.getConverterComponent().getEntityConverterContext());
        assertTrue(componentInfo.getConverterComponent().getEntityConverterContext().getEntityConverterStrategy()
            instanceof JpaEntityConverterStrategy);
        assertTrue(enableSwitch.getGenerateEntity());
        assertFalse(enableSwitch.getGenerateDTO());
        assertFalse(enableSwitch.getGenerateRepo());
        assertFalse(enableSwitch.getGenerateCustomRepo());
        assertFalse(enableSwitch.getGenerateService());
        assertFalse(enableSwitch.getGenerateServiceImpl());
        assertFalse(enableSwitch.getGenerateController());
        assertFalse(enableSwitch.getGenerateOpenApi3());
        assertEquals(2, config.getTableNames().size());
    }
}
