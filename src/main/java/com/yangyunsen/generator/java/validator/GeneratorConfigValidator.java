package com.yangyunsen.generator.java.validator;

import com.yangyunsen.generator.java.common.GeneratorException;
import com.yangyunsen.generator.java.common.model.dto.DatabaseInfo;
import com.yangyunsen.generator.java.common.model.dto.GeneratorConfig;
import com.yangyunsen.generator.java.common.model.enums.Mode;
import com.yangyunsen.generator.java.common.model.statics.CommonStatic;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局配置校验
 *
 * @author clouds3n
 * @date 2021-09-24
 */
public class GeneratorConfigValidator {

    public static void validConfig(GeneratorConfig fragmentaryConfig) {
        if (StringUtils.isBlank(fragmentaryConfig.getAuthor())) {
            throw new GeneratorException("请设置作者: builder.author(xxx)");
        }
        if (fragmentaryConfig.getMode() == null) {
            throw new GeneratorException("请设置生成器模式: builder.mode(xxx)");
        }
        if (fragmentaryConfig.getMode() == Mode.MP) {
            throw new GeneratorException("暂不支持Mybatis-Plus的代码生成, Coming Soon!");
        }
        if (CollectionUtils.isEmpty(fragmentaryConfig.getTableNames())) {
            System.out.print("请输入需要生成的表名(多个以英文逗号分隔): ");
            Scanner scanner= new Scanner(System.in);
            String tableNamesStr = scanner.nextLine();
            if (StringUtils.isBlank(tableNamesStr)) {
                throw new GeneratorException("请列出需要生成的表");
            }
            Set<String> tableNames = Arrays.stream(tableNamesStr.split(CommonStatic.COMMA))
                .map(String::trim)
                .collect(Collectors.toSet());
            fragmentaryConfig.setTableNames(tableNames);
        }
        validDatabaseInfo(fragmentaryConfig.getDatabaseInfo());
    }

    public static void validDatabaseInfo(DatabaseInfo databaseInfo) {
        boolean invalid = Objects.isNull(databaseInfo)
            || StringUtils.isAnyBlank(databaseInfo.getUrl(), databaseInfo.getUsername(), databaseInfo.getPasswd())
            || Objects.isNull(databaseInfo.getDriverPkgName());
        if (invalid) {
            throw new GeneratorException("缺少数据库连接信息");
        }
    }
}
