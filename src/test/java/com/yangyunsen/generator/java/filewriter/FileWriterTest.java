package com.yangyunsen.generator.java.filewriter;

import com.yangyunsen.generator.java.common.model.dto.DatabaseInfo;
import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.common.model.dto.PackageInfo;
import com.yangyunsen.generator.java.common.model.enums.JdbcDriverPkgName;
import com.yangyunsen.generator.java.common.model.enums.JdbcUrlPrefix;
import com.yangyunsen.generator.java.common.model.enums.Mode;
import com.yangyunsen.generator.java.converter.Converter;
import com.yangyunsen.generator.java.converter.EntityTemplateData;
import com.yangyunsen.generator.java.dbloader.DbLoader;
import com.yangyunsen.generator.java.dbloader.oracle.OracleColumnInfo;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author CloudS3n
 * @date 2021-09-30 14:25
 */
class FileWriterTest {

    @Test
    void writeEntityToDisk() {
        DatabaseInfo databaseInfo = new DatabaseInfo()
            .setUrl(JdbcUrlPrefix.ORACLE.getPrefix() + "172.20.254.14:1521:orcl")
            .setUsername("CQDX_JXGLXX")
            .setPasswd("cquisse")
            .setDriverPkgName(JdbcDriverPkgName.ORACLE);
        PackageInfo packageInfo = new PackageInfo()
            .setEntityPkgName("com.yangyunsen.demo.entity");
        GeneratorConfig generatorConfig = new GeneratorConfig()
            .setAuthor("CloudS3n")
            .setMode(Mode.JPA)
            .setTableNames(Set.of("TEST_GENERATOR"))
            .setDatabaseInfo(databaseInfo)
            .setPackageInfo(packageInfo);
        assertDoesNotThrow(() -> {
            Map<String, List<OracleColumnInfo>> tableColumnsMap = DbLoader.getColumnInfo(generatorConfig);
            List<EntityTemplateData> entityTemplateDataList = Converter.convert(generatorConfig, tableColumnsMap);
            FileWriter.writeEntityToDisk(generatorConfig, entityTemplateDataList);
        });
    }
}
