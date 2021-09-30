package com.yangyunsen.generator.java.common.model.statics;

import cn.hutool.system.SystemUtil;

import java.io.File;

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
    public static final String SRC_PATH = "src" + File.separator + "main" + File.separator + "java" + File.separator;
    public static final String JAVA_PATH = SystemUtil.getUserInfo().getCurrentDir() + SRC_PATH;
}
