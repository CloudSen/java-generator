package com.yangyunsen.generator.java.common.mapping;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Java类型和包全名的映射
 *
 * @author clouds3n
 * @date 2021-09-25
 */
@Getter
@RequiredArgsConstructor
public enum DefaultJavaTypePkgMapping implements JavaTypePkgMapping {

    //<editor-fold desc="基本类型">
    /**
     * byte
     */
    BASE_BYTE("byte", null),
    /**
     * short
     */
    BASE_SHORT("short", null),
    /**
     * char
     */
    BASE_CHAR("char", null),
    /**
     * int
     */
    BASE_INT("int", null),
    /**
     * long
     */
    BASE_LONG("long", null),
    /**
     * float
     */
    BASE_FLOAT("float", null),
    /**
     * double
     */
    BASE_DOUBLE("double", null),
    /**
     * boolean
     */
    BASE_BOOLEAN("boolean", null),
    //</editor-fold>

    //<editor-fold desc="包装类型">
    /**
     * Byte
     */
    BYTE("Byte", null),
    /**
     * Short
     */
    SHORT("Short", null),
    /**
     * Character
     */
    CHARACTER("Character", null),
    /**
     * Integer
     */
    INTEGER("Integer", null),
    /**
     * Long
     */
    LONG("Long", null),
    /**
     * Float
     */
    FLOAT("Float", null),
    /**
     * Double
     */
    DOUBLE("Double", null),
    /**
     * Boolean
     */
    BOOLEAN("Boolean", null),
    /**
     * String
     */
    STRING("String", null),
    //</editor-fold>

    //<editor-fold desc="java8+ 时间类型">
    /**
     * LocalDate
     */
    LOCAL_DATE("LocalDate", "java.time.LocalDate"),
    /**
     * LocalTime
     */
    LOCAL_TIME("LocalTime", "java.time.LocalTime"),
    /**
     * Year
     */
    YEAR("Year", "java.time.Year"),
    /**
     * YearMonth
     */
    YEAR_MONTH("YearMonth", "java.time.YearMonth"),
    /**
     * LocalDateTime
     */
    LOCAL_DATE_TIME("LocalDateTime", "java.time.LocalDateTime"),
    /**
     * Instant
     */
    INSTANT("Instant", "java.time.Instant"),
    //</editor-fold>

    //<editor-fold desc="大数据类">
    /**
     * BigInteger
     */
    BIG_INTEGER("BigInteger", "java.math.BigInteger"),
    /**
     * BigDecimal
     */
    BIG_DECIMAL("BigDecimal", "java.math.BigDecimal"),
    /**
     * Blob
     */
    BLOB("Blob", "java.sql.Blob"),
    /**
     * Clob
     */
    CLOB("Clob", "java.sql.Clob"),
    /**
     * 字节数组
     */
    BYTE_ARRAY("byte[]", null),
    //</editor-fold>
    ;

    /**
     * java 类型
     */
    private final String javaType;
    /**
     * 包全名
     */
    private final String pkgName;
}
