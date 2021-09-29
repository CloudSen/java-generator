package com.yangyunsen.generator.java.converter;

import com.yangyunsen.generator.java.common.model.dto.DatabaseInfo;
import com.yangyunsen.generator.java.common.model.dto.PackageInfo;
import com.yangyunsen.generator.java.common.model.enums.JdbcDriverPkgName;
import com.yangyunsen.generator.java.common.model.enums.JdbcUrlPrefix;
import com.yangyunsen.generator.java.converter.jpa.model.JpaEntityTemplateData;
import com.yangyunsen.generator.java.dbloader.DbLoader;
import com.yangyunsen.generator.java.dbloader.oracle.OracleColumnInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author clouds3n
 * @date 2021-09-29
 */
class ConverterTest {

    @Test
    void convert() {
        PackageInfo packageInfo = new PackageInfo().setEntityPkgName("com.yangyunsen.test");
        DatabaseInfo databaseInfo = new DatabaseInfo()
            .setUrl(JdbcUrlPrefix.ORACLE.getPrefix() + "172.20.254.14:1521:orcl")
            .setUsername("CQDX_JXGLXX")
            .setPasswd("cquisse")
            .setDriverPkgName(JdbcDriverPkgName.ORACLE);
        Set<String> tables = Set.of("TEST_GENERATOR");
        Map<String, List<OracleColumnInfo>> tableColumnsMap = DbLoader.getColumnInfo(databaseInfo, tables);
        Assertions.assertDoesNotThrow(() -> {
            List<JpaEntityTemplateData> entityTemplateData = Converter.convert(packageInfo, tableColumnsMap);
            System.out.println(entityTemplateData);
        });
    }
}
