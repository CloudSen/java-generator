package com.yangyunsen.generator.java.dbloader.oracle;

import com.yangyunsen.generator.java.common.JdbcDriverClass;
import com.yangyunsen.generator.java.common.JdbcUrlPrefix;
import com.yangyunsen.generator.java.dbloader.DatabaseLoader;
import com.yangyunsen.generator.java.dbloader.module.DatabaseInfo;
import org.apache.commons.lang3.BooleanUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Oracle数据库信息加载器测试类
 *
 * @author clouds3n
 * @date 2021-09-24
 */
class OracleDatabaseLoaderTest {

    @Test
    @DisplayName("Oracle数据库信息加载器——获取数据库连接")
    void getJdbcConnect() {
        Assertions.assertDoesNotThrow(() -> {
            DatabaseInfo databaseInfo = new DatabaseInfo()
                .setUrl(JdbcUrlPrefix.ORACLE.getPrefix() + "172.20.254.14:1521:orcl")
                .setUsername("CQDX_JXGLXX")
                .setPasswd("cquisse")
                .setDriverClassName(JdbcDriverClass.ORACLE);
            DatabaseLoader databaseLoader = new OracleDatabaseLoader(databaseInfo);
            Connection connection = databaseLoader.getJdbcConnection(databaseInfo);
            Assertions.assertNotNull(connection);
            Assertions.assertEquals(JdbcUrlPrefix.ORACLE.getPrefix() + "172.20.254.14:1521:orcl", connection.getMetaData().getURL());
            Assertions.assertEquals("CQDX_JXGLXX", connection.getMetaData().getUserName());
        });
    }

    @Test
    @DisplayName("Oracle数据库信息加载器——获取单个表信息")
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    void getOneTableInfo() {
        Assertions.assertDoesNotThrow(() -> {
            DatabaseInfo databaseInfo = new DatabaseInfo()
                .setUrl(JdbcUrlPrefix.ORACLE.getPrefix() + "172.20.254.14:1521:orcl")
                .setUsername("CQDX_JXGLXX")
                .setPasswd("cquisse")
                .setDriverClassName(JdbcDriverClass.ORACLE);
            DatabaseLoader databaseLoader = new OracleDatabaseLoader(databaseInfo);
            Map<String, List<OracleColumnInfo>> tableColumnsMap = databaseLoader.getMultiTableInfo(List.of("TEST_GENERATOR"));
            Assertions.assertNotNull(tableColumnsMap);
            Assertions.assertEquals(1, tableColumnsMap.keySet().size());
            Assertions.assertEquals(3, tableColumnsMap.values().stream().mapToLong(Collection::size).sum());
            Assertions.assertEquals(1, tableColumnsMap.values().stream().flatMap(Collection::stream).filter(c -> BooleanUtils.isTrue(c.getPkFlag())).count());
            Assertions.assertEquals("UNIQUE_ID", tableColumnsMap.values().stream().flatMap(Collection::stream).filter(c -> BooleanUtils.isTrue(c.getPkFlag())).findFirst().map(OracleColumnInfo::getColumnName).get());
        });
    }

    @Test
    @DisplayName("Oracle数据库信息加载器——获取多个表信息")
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    void getMultiTableInfo() {
        Assertions.assertDoesNotThrow(() -> {
            DatabaseInfo databaseInfo = new DatabaseInfo()
                .setUrl(JdbcUrlPrefix.ORACLE.getPrefix() + "172.20.254.14:1521:orcl")
                .setUsername("CQDX_JXGLXX")
                .setPasswd("cquisse")
                .setDriverClassName(JdbcDriverClass.ORACLE);
            DatabaseLoader databaseLoader = new OracleDatabaseLoader(databaseInfo);
            Map<String, List<OracleColumnInfo>> tableColumnsMap = databaseLoader.getMultiTableInfo(List.of("TEST_GENERATOR", "TEST_GENERATOR2"));
            Assertions.assertNotNull(tableColumnsMap);
            Assertions.assertEquals(2, tableColumnsMap.keySet().size());
            Assertions.assertEquals(6, tableColumnsMap.values().stream().mapToLong(Collection::size).sum());
            Assertions.assertEquals(2, tableColumnsMap.values().stream().flatMap(Collection::stream).filter(c -> BooleanUtils.isTrue(c.getPkFlag())).count());
            Assertions.assertEquals("UNIQUE_ID", tableColumnsMap.get("TEST_GENERATOR").stream().filter(c -> BooleanUtils.isTrue(c.getPkFlag())).findFirst().map(OracleColumnInfo::getColumnName).get());
            Assertions.assertEquals("UNIQUE_ID", tableColumnsMap.get("TEST_GENERATOR2").stream().filter(c -> BooleanUtils.isTrue(c.getPkFlag())).findFirst().map(OracleColumnInfo::getColumnName).get());
        });
    }
}
