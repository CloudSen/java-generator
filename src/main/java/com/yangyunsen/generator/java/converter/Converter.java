package com.yangyunsen.generator.java.converter;

import com.yangyunsen.generator.java.common.model.dto.PackageInfo;
import com.yangyunsen.generator.java.converter.jpa.EntityConverterStrategy;
import com.yangyunsen.generator.java.converter.jpa.model.JpaEntityTemplateData;
import com.yangyunsen.generator.java.dbloader.oracle.OracleColumnInfo;

import java.util.List;
import java.util.Map;

/**
 * @author clouds3n
 * @date 2021-09-29
 */
public class Converter {

    public static List<JpaEntityTemplateData> convert(PackageInfo packageInfo, Map<String, List<OracleColumnInfo>> tableColumnsMap) {
        return new ConverterContext(new EntityConverterStrategy()).convertEntity(packageInfo, tableColumnsMap);
    }
}
