package com.yangyunsen.generator.java.converter.jpa;

import com.yangyunsen.generator.java.common.FreeMarkerManager;
import com.yangyunsen.generator.java.converter.jpa.model.EntityInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * ORM实体类转换器
 *
 * @author clouds3n
 * @date 2021-09-27
 */
public class EntityConverter {

    public static void convert(EntityInfo entityInfo) throws IOException, TemplateException {
        Configuration fmc = FreeMarkerManager.getConfig();
        Template temp = fmc.getTemplate("entity.ftl");
        Writer out = new OutputStreamWriter(System.out);
        temp.process(entityInfo, out);
    }
}
