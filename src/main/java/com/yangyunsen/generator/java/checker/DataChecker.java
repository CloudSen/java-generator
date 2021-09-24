package com.yangyunsen.generator.java.checker;

import com.yangyunsen.generator.java.common.GeneratorException;
import com.yangyunsen.generator.java.dbloader.module.DatabaseInfo;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * 信息校验
 *
 * @author clouds3n
 * @date 2021-09-24
 */
public class DataChecker {

    public static void checkDatabaseParams(DatabaseInfo databaseInfo) {
        boolean invalid = Objects.isNull(databaseInfo)
            || StringUtils.isAnyBlank(databaseInfo.getUrl(), databaseInfo.getUsername(), databaseInfo.getPasswd())
            || Objects.isNull(databaseInfo.getDriverClassName());
        if (invalid) {
            throw new GeneratorException("缺少数据库连接信息");
        }
    }
}
