package com.yangyunsen.generator.java.dbloader.oracle;

import com.yangyunsen.generator.java.common.model.dto.DatabaseInfo;
import com.yangyunsen.generator.java.common.model.enums.JdbcDriverPkgName;
import com.yangyunsen.generator.java.common.model.enums.JdbcUrlPrefix;
import com.yangyunsen.generator.java.dbloader.DatabaseLoaderStrategy;
import org.apache.commons.lang3.BooleanUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Oracle数据库信息加载器测试类
 *
 * @author clouds3n
 * @date 2021-09-24
 */
class OracleDatabaseLoaderStrategyTest {

    @Test
    @DisplayName("Oracle数据库信息加载器——获取数据库连接")
    void getJdbcConnect() {
        Assertions.assertDoesNotThrow(() -> {
            DatabaseInfo databaseInfo = new DatabaseInfo()
                .setUrl(JdbcUrlPrefix.ORACLE.getPrefix() + "172.20.254.14:1521:orcl")
                .setUsername("CQDX_JXGLXX")
                .setPasswd("cquisse")
                .setDriverPkgName(JdbcDriverPkgName.ORACLE);
            DatabaseLoaderStrategy databaseLoaderStrategy = new OracleDatabaseLoaderStrategy(databaseInfo);
            Connection connection = databaseLoaderStrategy.getJdbcConnection(databaseInfo);
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
                .setDriverPkgName(JdbcDriverPkgName.ORACLE);
            DatabaseLoaderStrategy databaseLoaderStrategy = new OracleDatabaseLoaderStrategy(databaseInfo);
            Map<String, List<OracleColumnInfo>> tableColumnsMap = databaseLoaderStrategy.getMultiTableInfo(Set.of("TEST_GENERATOR"));
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
                .setDriverPkgName(JdbcDriverPkgName.ORACLE);
            DatabaseLoaderStrategy databaseLoaderStrategy = new OracleDatabaseLoaderStrategy(databaseInfo);
            Map<String, List<OracleColumnInfo>> tableColumnsMap = databaseLoaderStrategy.getMultiTableInfo(Set.of("TEST_GENERATOR", "TEST_GENERATOR2"));
            Assertions.assertNotNull(tableColumnsMap);
            Assertions.assertEquals(2, tableColumnsMap.keySet().size());
            Assertions.assertEquals(6, tableColumnsMap.values().stream().mapToLong(Collection::size).sum());
            Assertions.assertEquals(2, tableColumnsMap.values().stream().flatMap(Collection::stream).filter(c -> BooleanUtils.isTrue(c.getPkFlag())).count());
            Assertions.assertEquals("UNIQUE_ID", tableColumnsMap.get("TEST_GENERATOR").stream().filter(c -> BooleanUtils.isTrue(c.getPkFlag())).findFirst().map(OracleColumnInfo::getColumnName).get());
            Assertions.assertEquals("UNIQUE_ID", tableColumnsMap.get("TEST_GENERATOR2").stream().filter(c -> BooleanUtils.isTrue(c.getPkFlag())).findFirst().map(OracleColumnInfo::getColumnName).get());
        });
    }
}
