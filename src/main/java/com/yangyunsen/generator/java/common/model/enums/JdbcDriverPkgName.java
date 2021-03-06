package com.yangyunsen.generator.java.common.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * JDB驱动全包名
 *
 * @author clouds3n
 * @date 2021-09-24
 */
@Getter
@RequiredArgsConstructor
public enum JdbcDriverPkgName {

    /**
     * mysql驱动类名
     */
    MYSQL("com.mysql.cj.jdbc.Driver"),
    /**
     * oracle驱动类名
     */
    ORACLE("oracle.jdbc.driver.OracleDriver"),
    /**
     * pgsql驱动类名
     */
    POSTGRE_SQL("org.postgresql.Driver"),
    /**
     * mariadb驱动类名
     */
    MARIADB("org.mariadb.jdbc.Driver"),
    ;

    private final String fullName;
}
