package com.yangyunsen.generator.java.dbloader;

import com.yangyunsen.generator.java.dbloader.oracle.OracleColumnInfo;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author clouds3n
 * @date 2021-09-29
 */
@RequiredArgsConstructor
public class DbLoaderContext {

    private final DatabaseLoaderStrategy databaseLoaderStrategy;

    public Map<String, List<OracleColumnInfo>> getMultiTableInfo(Set<String> tableNames) {
        return databaseLoaderStrategy.getMultiTableInfo(tableNames);
    }
}
