package com.yangyunsen.generator.java.converter.jpa;

import com.yangyunsen.generator.java.converter.jpa.model.EntityField;
import com.yangyunsen.generator.java.converter.jpa.model.JpaEntityTemplateData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author clouds3n
 * @date 2021-09-27
 */
class EntityConverterStrategyTest {

    @Test
    void convert() {
        JpaEntityTemplateData jpaEntityTemplateData = new JpaEntityTemplateData()
            .setTableName("TEST_GENERATOR")
            .setClassName("TestGenerator")
            .setPkgName("com.yangyunsen.test")
            .setFields(Arrays.asList(
                new EntityField()
                    .setPkFlg(true)
                    .setDbName("UNIQUEID")
                    .setJavaName("id")
                    .setJavaType("String"),
                new EntityField()
                    .setPkFlg(null)
                    .setDbName("NAME")
                    .setJavaName("name")
                    .setJavaType("String"),
                new EntityField()
                    .setPkFlg(false)
                    .setDbName("AGE")
                    .setJavaName("age")
                    .setJavaType("Integer")
            ));
//        Assertions.assertDoesNotThrow(() -> EntityConverter.convert(entityTemplateData));
    }
}
