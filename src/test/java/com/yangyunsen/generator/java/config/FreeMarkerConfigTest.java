package com.yangyunsen.generator.java.config;

import com.yangyunsen.generator.java.dbloader.oracle.OracleColumnInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * 测试FreeMarker配置类
 *
 * @author clouds3n
 * @date 2021-09-23
 */
public class FreeMarkerConfigTest {

    @Test
    @Order(1)
    @DisplayName("单例测试通过")
    void testSingleton() {
        Configuration fmc1 = FreeMarkerManager.getConfig();
        Configuration fmc2 = FreeMarkerManager.getConfig();
        Configuration fmc3 = FreeMarkerManager.getConfig();
        Assertions.assertEquals(fmc1, fmc2);
        Assertions.assertEquals(fmc1, fmc3);
        Assertions.assertEquals(fmc3, fmc2);
    }

    @Test
    @Order(2)
    @DisplayName("读取模板并渲染通过")
    void testMerge() throws IOException, TemplateException {
        Configuration fmc = FreeMarkerManager.getConfig();
        Template temp = fmc.getTemplate("test.ftl");
        Writer out = new OutputStreamWriter(System.out);
        OracleColumnInfo table = new OracleColumnInfo().setTableName("test");
        temp.process(table, out);
    }
}
