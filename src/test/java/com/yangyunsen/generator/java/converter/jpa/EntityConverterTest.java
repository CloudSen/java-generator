package com.yangyunsen.generator.java.converter.jpa;

import com.yangyunsen.generator.java.converter.jpa.model.EntityField;
import com.yangyunsen.generator.java.converter.jpa.model.EntityInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author clouds3n
 * @date 2021-09-27
 */
class EntityConverterTest {

    @Test
    void convert() {
        EntityInfo entityInfo = new EntityInfo()
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
        Assertions.assertDoesNotThrow(() -> EntityConverter.convert(entityInfo));
    }
}
