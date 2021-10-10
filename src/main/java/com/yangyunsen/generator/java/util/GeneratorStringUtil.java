package com.yangyunsen.generator.java.util;

import cn.hutool.system.SystemUtil;
import com.yangyunsen.generator.java.common.GeneratorException;
import com.yangyunsen.generator.java.common.model.dto.PackageInfo;
import com.yangyunsen.generator.java.common.model.enums.MvcLevel;
import com.yangyunsen.generator.java.common.model.statics.CommonStatic;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.CaseUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 字符串工具
 *
 * @author CloudS3n
 * @date 2021-10-09 09:28
 */
public class GeneratorStringUtil {

    public static String tableNameToClassName(String tableName, MvcLevel mvcLevel) {
        String className = CaseUtils.toCamelCase(tableName, true, CommonStatic.UL_C);
        switch (mvcLevel) {
            case ENTITY:
                return className + CommonStatic.ENTITY_CLASS_SUFFIX;
            case DTO:
                return className + CommonStatic.DTO_CLASS_SUFFIX;
            case REPO:
                return className + CommonStatic.REPO_CLASS_SUFFIX;
            case REPO_IMPL:
                return className + CommonStatic.REPO_IMPL_CLASS_SUFFIX;
            case CUSTOM_REPO:
                return className + CommonStatic.CUSTOM_REPO_CLASS_SUFFIX;
            case CUSTOM_REPO_IMPL:
                return className + CommonStatic.CUSTOM_REPO_IMPL_CLASS_SUFFIX;
            case SERVICE:
                return className + CommonStatic.SERVICE_CLASS_SUFFIX;
            case SERVICE_IMPL:
                return className + CommonStatic.SERVICE_IMPL_CLASS_SUFFIX;
            case CONTROLLER:
                return className + CommonStatic.CONTROLLER_CLASS_SUFFIX;
            default:
                throw new GeneratorException("不支持的mvc层级: " + mvcLevel);
        }
    }

    public static String tableNameToControllerUrl(String tableName) {
        return Arrays.stream(tableName.split(CommonStatic.UL))
            .map(StringUtils::lowerCase)
            .collect(Collectors.joining(CommonStatic.ML));
    }

    public static String dbColumnNameToJavaName(String columnName) {
        return CaseUtils.toCamelCase(columnName, false, CommonStatic.UL_C);
    }

    public static String getParentPkgName(String currentPkgName) {
        return currentPkgName.substring(0, currentPkgName.lastIndexOf(CommonStatic.DOT));
    }

    public static String getBrotherMvcPkgName(String parentPkgName, String tableName, MvcLevel wantedMvcLevel) {
        switch (wantedMvcLevel) {
            case ENTITY:
                return parentPkgName + CommonStatic.DOT + CommonStatic.ENTITY_PKG_NAME + CommonStatic.DOT
                    + tableNameToClassName(tableName, wantedMvcLevel);
            case DTO:
                return parentPkgName + CommonStatic.DOT + CommonStatic.MODEL_DTO_PKG_NAME + CommonStatic.DOT
                    + tableNameToClassName(tableName, wantedMvcLevel);
            case REPO:
                return parentPkgName + CommonStatic.DOT + CommonStatic.REPO_PKG_NAME + CommonStatic.DOT
                    + tableNameToClassName(tableName, wantedMvcLevel);
            case REPO_IMPL:
                return parentPkgName + CommonStatic.DOT + CommonStatic.REPO_IMPL_PKG_NAME + CommonStatic.DOT
                    + tableNameToClassName(tableName, wantedMvcLevel);
            case CUSTOM_REPO:
                return parentPkgName + CommonStatic.DOT + CommonStatic.CUSTOM_REPO_PKG_NAME + CommonStatic.DOT
                    + tableNameToClassName(tableName, wantedMvcLevel);
            case CUSTOM_REPO_IMPL:
                return parentPkgName + CommonStatic.DOT + CommonStatic.CUSTOM_REPO_IMPL_PKG_NAME + CommonStatic.DOT
                    + tableNameToClassName(tableName, wantedMvcLevel);
            case SERVICE:
                return parentPkgName + CommonStatic.DOT + CommonStatic.SERVICE_PKG_NAME + CommonStatic.DOT
                    + tableNameToClassName(tableName, wantedMvcLevel);
            case SERVICE_IMPL:
                return parentPkgName + CommonStatic.DOT + CommonStatic.SERVICE_IMPL_PKG_NAME + CommonStatic.DOT
                    + tableNameToClassName(tableName, wantedMvcLevel);
            case CONTROLLER:
                return parentPkgName + CommonStatic.DOT + CommonStatic.CONTROLLER_PKG_NAME + CommonStatic.DOT
                    + tableNameToClassName(tableName, wantedMvcLevel);
            default:
                throw new GeneratorException("不支持的mvc层级: " + wantedMvcLevel);
        }
    }

    public static String parsePkgNameToPath(String pkgName) {
        return SystemUtil.getOsInfo().isWindows() ?
            CommonStatic.SLASH + CommonStatic.JAVA_PATH.replaceAll("\\\\", CommonStatic.SLASH) + pkgName.replaceAll("\\.", CommonStatic.SLASH) + CommonStatic.SLASH
            : CommonStatic.JAVA_PATH + pkgName.replaceAll("\\.", CommonStatic.SLASH) + CommonStatic.SLASH;
    }

    public static String getPathStrByMvcLevel(PackageInfo packageInfo, MvcLevel mvcLevel) {
        switch (mvcLevel) {
            case ENTITY:
                return GeneratorStringUtil.parsePkgNameToPath(packageInfo.getEntityPkgName());
            case DTO:
                return GeneratorStringUtil.parsePkgNameToPath(packageInfo.getDtoPkgName());
            case REPO:
                return GeneratorStringUtil.parsePkgNameToPath(packageInfo.getRepoPkgName());
            case REPO_IMPL:
                return GeneratorStringUtil.parsePkgNameToPath(packageInfo.getRepoImplPkgName());
            case CUSTOM_REPO:
                return GeneratorStringUtil.parsePkgNameToPath(packageInfo.getCustomRepoPkgName());
            case CUSTOM_REPO_IMPL:
                return GeneratorStringUtil.parsePkgNameToPath(packageInfo.getCustomRepoImplPkgName());
            case SERVICE:
                return GeneratorStringUtil.parsePkgNameToPath(packageInfo.getServicePkgName());
            case SERVICE_IMPL:
                return GeneratorStringUtil.parsePkgNameToPath(packageInfo.getServiceImplPkgName());
            case CONTROLLER:
                return GeneratorStringUtil.parsePkgNameToPath(packageInfo.getControllerPkgName());
            default:
                throw new GeneratorException("不支持的mvc层级: " + mvcLevel);
        }
    }
}
