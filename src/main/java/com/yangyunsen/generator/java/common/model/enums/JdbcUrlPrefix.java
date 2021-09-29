package com.yangyunsen.generator.java.common.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * JDBC URL前缀
 *
 * @author clouds3n
 * @date 2021-09-24
 */
@Getter
@RequiredArgsConstructor
public enum JdbcUrlPrefix {

    /**
     * mysql连接前缀
     */
    MYSQL("jdbc:mysql://"),
    /**
     * oracle连接前缀
     */
    ORACLE("jdbc:oracle:thin:@"),
    /**
     * pgsql连接前缀
     */
    POSTGRE_SQL("jdbc:postgresql://"),
    /**
     * mariadb连接前缀
     */
    MARIADB("jdbc:mariadb://"),
    ;

    private final String prefix;
}
