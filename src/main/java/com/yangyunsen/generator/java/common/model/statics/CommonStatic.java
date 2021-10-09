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
    public static final String ML = "-";
    public static final String SLASH = "/";
    public static final String SRC_PATH = "src/main/java/";
    public static final String JAVA_PATH = SystemUtil.getUserInfo().getCurrentDir() + SRC_PATH;
    public static final String JAVA_FILE_SUFFIX = ".java";
    public static final String FILE_SCHEMA = "file:";

    //<editor-fold desc="class name suffix">

    public static final String ENTITY_CLASS_SUFFIX = "Entity";
    public static final String DTO_CLASS_SUFFIX = "DTO";
    public static final String REPO_CLASS_SUFFIX = "Repository";
    public static final String REPO_IMPL_CLASS_SUFFIX = "RepositoryImpl";
    public static final String CUSTOM_REPO_CLASS_SUFFIX = "CustomRepository";
    public static final String CUSTOM_REPO_IMPL_CLASS_SUFFIX = "CustomRepositoryImpl";
    public static final String SERVICE_CLASS_SUFFIX = "Service";
    public static final String SERVICE_IMPL_CLASS_SUFFIX = "ServiceImpl";
    public static final String CONTROLLER_CLASS_SUFFIX = "Controller";

    //</editor-fold>

    //<editor-fold desc="mvc package name">

    public static final String ENTITY_PKG_NAME = "entity";
    public static final String MODEL_DTO_PKG_NAME = "model.dto";
    public static final String REPO_PKG_NAME = "repository";
    public static final String REPO_IMPL_PKG_NAME = "repository.impl";
    public static final String CUSTOM_REPO_PKG_NAME = "repository.custom";
    public static final String CUSTOM_REPO_IMPL_PKG_NAME = "repository.custom.impl";
    public static final String SERVICE_PKG_NAME = "service";
    public static final String SERVICE_IMPL_PKG_NAME = "service.impl";
    public static final String CONTROLLER_PKG_NAME = "controller";

    //</editor-fold>

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
