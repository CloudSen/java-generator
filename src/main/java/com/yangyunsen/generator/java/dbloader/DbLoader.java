package com.yangyunsen.generator.java.dbloader;

import com.yangyunsen.generator.java.common.GeneratorException;
import com.yangyunsen.generator.java.common.model.dto.DatabaseInfo;
import com.yangyunsen.generator.java.common.model.enums.JdbcDriverPkgName;
import com.yangyunsen.generator.java.dbloader.oracle.OracleColumnInfo;
import com.yangyunsen.generator.java.dbloader.oracle.OracleDatabaseLoaderStrategy;

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

    public static Map<String, List<OracleColumnInfo>> getColumnInfo(DatabaseInfo databaseInfo, Set<String> tableNames) {
        JdbcDriverPkgName driverPkgName = databaseInfo.getDriverPkgName();
        if (driverPkgName == JdbcDriverPkgName.ORACLE) {
            return new DbLoaderContext(new OracleDatabaseLoaderStrategy(databaseInfo)).getMultiTableInfo(tableNames);
        } else {
            throw new GeneratorException("暂不支持" + driverPkgName.name() + "数据库的代码生成");
        }
    }
}
