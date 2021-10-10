package com.yangyunsen.generator.java.dbloader;

import com.yangyunsen.generator.java.common.model.dto.DatabaseInfo;
import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.common.model.enums.JdbcDriverPkgName;
import com.yangyunsen.generator.java.dbloader.oracle.OracleColumnInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 数据库加载器入口
 *
 * @author clouds3n
 * @date 2021-09-29
 */
public class DbLoader {

    public static Map<String, List<OracleColumnInfo>> getColumnInfo(GeneratorConfig generatorConfig) {
        final DatabaseInfo databaseInfo = generatorConfig.getDatabaseInfo();
        final Set<String> tableNames = generatorConfig.getTableNames();
        JdbcDriverPkgName driverPkgName = databaseInfo.getDriverPkgName();
        DbLoaderContext dbLoaderContext = generatorConfig.getComponentInfo().getDbLoaderContext();
        return dbLoaderContext.getMultiTableInfo(tableNames);
    }
}
