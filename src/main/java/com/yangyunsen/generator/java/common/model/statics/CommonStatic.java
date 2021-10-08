package com.yangyunsen.generator.java.common.model.statics;

import cn.hutool.system.SystemUtil;

/**
 * 公共常量
 *
 * @author clouds3n
 * @date 2021-09-24
 */
public final class CommonStatic {

    public static final String COMMA = ",";
    public static final String DOT = ".";
    public static final String LP = "(";
    public static final String RP = ")";
    public static final String UL = "_";
    public static final Character UL_C = '_';
    public static final String SLASH = "/";
    public static final String SRC_PATH = "src/main/java/";
    public static final String JAVA_PATH = SystemUtil.getUserInfo().getCurrentDir() + SRC_PATH;
    public static final String JAVA_FILE_SUFFIX = ".java";
    public static final String FILE_SCHEMA = "file:";
    public static final String ENTITY_CLASS_SUFFIX = "Entity";
    public static final String DATE_STR = "date";
    public static final String TIMESTAMP_STR = "timestamp";
    public static final String NUMBER_STR = "number";
    public static final String FLOAT_STR = "float";
    public static final String BLOB_STR = "blob";
    public static final String BINARY_STR = "binary";
    public static final String RAW_STR = "raw";
    public static final String VARCHAR_STR = "varchar";
    public static final Integer INT_10 = 10;
    public static final Integer INT_19 = 19;
}
