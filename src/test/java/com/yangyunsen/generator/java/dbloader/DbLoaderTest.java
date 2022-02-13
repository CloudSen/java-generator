package com.yangyunsen.generator.java.dbloader;

import com.yangyunsen.generator.java.common.GeneratorException;
import com.yangyunsen.generator.java.common.model.dto.DatabaseInfo;
import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.common.model.dto.PackageInfo;
import com.yangyunsen.generator.java.common.model.enums.JdbcDriverPkgName;
import com.yangyunsen.generator.java.common.model.enums.JdbcUrlPrefix;
import com.yangyunsen.generator.java.common.model.enums.Mode;
import com.yangyunsen.generator.java.dbloader.oracle.OracleColumnInfo;
import org.apache.commons.lang3.BooleanUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author clouds3n
 * @date 2021-09-29
 */
class DbLoaderTest {

    @Test
    @DisplayName("数据库加载器-加载不支持的数据库")
    void loadNotSupportedDb() {
        Assertions.assertThrows(GeneratorException.class, () -> {
            DatabaseInfo databaseInfo = new DatabaseInfo()
                .setUrl(JdbcUrlPrefix.ORACLE.getPrefix() + "192.168.2.145:1521:orcl")
                .setUsername("CQDX_JXGLXX")
                .setPasswd("cquisse")
                .setDriverPkgName(JdbcDriverPkgName.POSTGRE_SQL);
            PackageInfo packageInfo = new PackageInfo()
                .setEntityPkgName("com.yangyunsen.demo.entity");
            GeneratorConfig generatorConfig = GeneratorConfig.builder()
                .author("CloudS3n")
                .mode(Mode.JPA)
                .tableNames(Set.of("TEST_GENERATOR"))
                .databaseInfo(databaseInfo)
                .packageInfo(packageInfo)
                .build();
            DbLoader.getColumnInfo(generatorConfig);
        });
    }

    @Test
    @DisplayName("数据库加载器-加载Oracle单个表")
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    void getOneTableColumnInfo() {
        DatabaseInfo databaseInfo = new DatabaseInfo()
            .setUrl(JdbcUrlPrefix.ORACLE.getPrefix() + "192.168.2.145:1521:orcl")
            .setUsername("CQDX_JXGLXX")
            .setPasswd("cquisse")
            .setDriverPkgName(JdbcDriverPkgName.ORACLE);
        PackageInfo packageInfo = new PackageInfo()
            .setEntityPkgName("com.yangyunsen.demo.entity");
        GeneratorConfig generatorConfig = GeneratorConfig.builder()
            .author("CloudS3n")
            .mode(Mode.JPA)
            .tableNames(Set.of("TEST_GENERATOR"))
            .databaseInfo(databaseInfo)
            .packageInfo(packageInfo)
            .build();
        Assertions.assertDoesNotThrow(() -> {
            Map<String, List<OracleColumnInfo>> tableColumnsMap = DbLoader.getColumnInfo(generatorConfig);
            Assertions.assertNotNull(tableColumnsMap);
            Assertions.assertEquals(1, tableColumnsMap.keySet().size());
            Assertions.assertEquals(3, tableColumnsMap.values().stream().mapToLong(Collection::size).sum());
            Assertions.assertEquals(1, tableColumnsMap.values().stream().flatMap(Collection::stream).filter(c -> BooleanUtils.isTrue(c.getPkFlag())).count());
            Assertions.assertEquals("UNIQUE_ID", tableColumnsMap.values().stream().flatMap(Collection::stream).filter(c -> BooleanUtils.isTrue(c.getPkFlag())).findFirst().map(OracleColumnInfo::getColumnName).get());
        });
    }

    @Test
    @DisplayName("数据库加载器-加载Oracle多个表")
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    void getMultiTableColumnInfo() {
        DatabaseInfo databaseInfo = new DatabaseInfo()
            .setUrl(JdbcUrlPrefix.ORACLE.getPrefix() + "192.168.2.145:1521:orcl")
            .setUsername("CQDX_JXGLXX")
            .setPasswd("cquisse")
            .setDriverPkgName(JdbcDriverPkgName.ORACLE);
        PackageInfo packageInfo = new PackageInfo()
            .setEntityPkgName("com.yangyunsen.demo.entity");
        GeneratorConfig generatorConfig = GeneratorConfig.builder()
            .author("CloudS3n")
            .mode(Mode.JPA)
            .tableNames(Set.of("TEST_GENERATOR", "TEST_GENERATOR2"))
            .databaseInfo(databaseInfo)
            .packageInfo(packageInfo)
            .build();
        Assertions.assertDoesNotThrow(() -> {
            Map<String, List<OracleColumnInfo>> tableColumnsMap = DbLoader.getColumnInfo(generatorConfig);
            Assertions.assertNotNull(tableColumnsMap);
            Assertions.assertEquals(2, tableColumnsMap.keySet().size());
            Assertions.assertEquals(6, tableColumnsMap.values().stream().mapToLong(Collection::size).sum());
            Assertions.assertEquals(2, tableColumnsMap.values().stream().flatMap(Collection::stream).filter(c -> BooleanUtils.isTrue(c.getPkFlag())).count());
            Assertions.assertEquals("UNIQUE_ID", tableColumnsMap.get("TEST_GENERATOR").stream().filter(c -> BooleanUtils.isTrue(c.getPkFlag())).findFirst().map(OracleColumnInfo::getColumnName).get());
            Assertions.assertEquals("UNIQUE_ID", tableColumnsMap.get("TEST_GENERATOR2").stream().filter(c -> BooleanUtils.isTrue(c.getPkFlag())).findFirst().map(OracleColumnInfo::getColumnName).get());
        });
    }
}
