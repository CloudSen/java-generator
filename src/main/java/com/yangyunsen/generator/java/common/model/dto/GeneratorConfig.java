package com.yangyunsen.generator.java.common.model.dto;

import com.yangyunsen.generator.java.common.GeneratorException;
import com.yangyunsen.generator.java.common.model.enums.JdbcDriverPkgName;
import com.yangyunsen.generator.java.common.model.enums.Mode;
import com.yangyunsen.generator.java.converter.ControllerConverterContext;
import com.yangyunsen.generator.java.converter.EntityConverterContext;
import com.yangyunsen.generator.java.converter.impl.common.CommonControllerConverterStrategy;
import com.yangyunsen.generator.java.converter.impl.jpa.entity.JpaEntityConverterStrategy;
import com.yangyunsen.generator.java.dbloader.DbLoaderContext;
import com.yangyunsen.generator.java.dbloader.oracle.OracleDatabaseLoaderStrategy;
import com.yangyunsen.generator.java.validator.GeneratorConfigValidator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * 生成器配置
 *
 * @author clouds3n
 * @date 2021-09-29
 */
@Setter
@Getter
@Builder(toBuilder = true)
public class GeneratorConfig {

    /**
     * 代码作者
     */
    private String author;
    /**
     * 生成器模式，JAP OR MP, 默认JPA
     */
    @Builder.Default
    private Mode mode = Mode.JPA;
    /**
     * 包信息
     */
    private PackageInfo packageInfo;
    /**
     * 数据库连接信息
     */
    private DatabaseInfo databaseInfo;
    /**
     * 需要生成的表名列表, 交互赋值
     */
    private Set<String> tableNames;
    /**
     * 组件信息, 有默认配置
     */
    private ComponentInfo componentInfo;
    /**
     * 启用开关信息, 默认生成配置了pkg的，但不生成OpenApi3文档
     */
    private EnableSwitch enableSwitch;

    public static GeneratorConfigBuilder builder() {
        return new CustomGeneratorConfigBuilder();
    }

    @Getter
    private static class CustomGeneratorConfigBuilder extends GeneratorConfigBuilder {
        @Override
        public GeneratorConfig build() {
            GeneratorConfig fragmentaryConfig = super.build();
            GeneratorConfigValidator.validConfig(fragmentaryConfig);
            Mode mode = fragmentaryConfig.getMode();
            //<editor-fold desc="设置默认配置">
            if (fragmentaryConfig.getEnableSwitch() == null) {
                PackageInfo packageInfo = fragmentaryConfig.getPackageInfo();
                EnableSwitch enableSwitch = new EnableSwitch()
                    .setGenerateEntity(StringUtils.isNotBlank(packageInfo.getEntityPkgName()))
                    .setGenerateDTO(StringUtils.isNotBlank(packageInfo.getDtoPkgName()))
                    .setGenerateRepo(StringUtils.isNotBlank(packageInfo.getRepoPkgName()))
                    .setGenerateCustomRepo(StringUtils.isNotBlank(packageInfo.getCustomRepoPkgName()))
                    .setGenerateService(StringUtils.isNotBlank(packageInfo.getServicePkgName()))
                    .setGenerateServiceImpl(StringUtils.isNotBlank(packageInfo.getServiceImplPkgName()))
                    .setGenerateController(StringUtils.isNotBlank(packageInfo.getControllerPkgName()));
                fragmentaryConfig.setEnableSwitch(enableSwitch);
            }
            if (fragmentaryConfig.getComponentInfo() == null) {
                ConverterComponent converterComponent = new ConverterComponent()
                    .setEntityConverterContext(new EntityConverterContext(mode == Mode.JPA ? new JpaEntityConverterStrategy() : null))
                    .setControllerConverterContext(new ControllerConverterContext(new CommonControllerConverterStrategy()));
                DatabaseInfo databaseInfo = fragmentaryConfig.getDatabaseInfo();
                JdbcDriverPkgName driverPkgName = databaseInfo.getDriverPkgName();
                DbLoaderContext dbLoaderContext;
                if (driverPkgName == JdbcDriverPkgName.ORACLE) {
                    dbLoaderContext = new DbLoaderContext(new OracleDatabaseLoaderStrategy(databaseInfo));
                } else {
                    throw new GeneratorException("暂不支持" + driverPkgName.name() + "数据库的代码生成");
                }
                ComponentInfo componentInfo = new ComponentInfo()
                    .setConverterComponent(converterComponent)
                    .setDbLoaderContext(dbLoaderContext);
                fragmentaryConfig.setComponentInfo(componentInfo);
            }
            //</editor-fold>
            return fragmentaryConfig;
        }
    }
}
