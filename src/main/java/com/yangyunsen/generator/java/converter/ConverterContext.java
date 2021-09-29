package com.yangyunsen.generator.java.converter;

import com.yangyunsen.generator.java.common.model.dto.PackageInfo;
import com.yangyunsen.generator.java.converter.jpa.model.JpaEntityTemplateData;
import com.yangyunsen.generator.java.dbloader.oracle.OracleColumnInfo;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author clouds3n
 * @date 2021-09-29
 */
@RequiredArgsConstructor
public class ConverterContext {

    private final ConverterStrategy converterStrategy;

    public List<JpaEntityTemplateData> convertEntity(PackageInfo packageInfo, Map<String, List<OracleColumnInfo>> tableColumnsMap) {
        return converterStrategy.convert(packageInfo, tableColumnsMap);
    }
}