package com.yangyunsen.generator.java.dbloader.oracle;

import com.yangyunsen.generator.java.common.JdbcDriverClass;
import com.yangyunsen.generator.java.common.JdbcUrlPrefix;
import com.yangyunsen.generator.java.dbloader.ColumnInfo;
import com.yangyunsen.generator.java.dbloader.DatabaseLoader;
import com.yangyunsen.generator.java.dbloader.module.DatabaseInfo;
import org.apache.commons.lang3.BooleanUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("Oracle数据库信息加载器——获取单个表信息通过")
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    void getMultiTableInfo() {
        DatabaseInfo databaseInfo = new DatabaseInfo()
                .setUrl(JdbcUrlPrefix.ORACLE.getPrefix() + "localhost:1523:orcl")
                .setUsername("clouds3n")
                .setPasswd("passwd")
                .setDriverClassName(JdbcDriverClass.ORACLE);
        DatabaseLoader databaseLoader = new OracleDatabaseLoader(databaseInfo);
        Map<String, List<ColumnInfo>> test = databaseLoader.getMultiTableInfo(List.of("TEST"));
        Assertions.assertNotNull(test);
        Assertions.assertEquals(test.keySet().size(), 1);
        Assertions.assertEquals(test.values().size(), 3);
        Assertions.assertEquals(test.values().stream().flatMap(Collection::stream).filter(c -> BooleanUtils.isTrue(c.getPkFlag())).count(), 1);
        Assertions.assertEquals(test.values().stream().flatMap(Collection::stream).filter(c -> BooleanUtils.isTrue(c.getPkFlag())).findFirst().map(ColumnInfo::getColumnName).get(), "id");
    }
}
