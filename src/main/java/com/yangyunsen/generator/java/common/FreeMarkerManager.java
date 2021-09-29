package com.yangyunsen.generator.java.common;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * FreeMarker模板配置
 *
 * @author clouds3n
 * @date 2021-09-23
 */
@Slf4j
public class FreeMarkerManager {

    private FreeMarkerManager() {
    }

    public static Configuration getConfig() {
        return InstanceHolder.FREE_MARKER_CONFIG;
    }

    private static class InstanceHolder {
        private static final Configuration FREE_MARKER_CONFIG = new Configuration(Configuration.VERSION_2_3_31);
        static {
            try {
                FREE_MARKER_CONFIG.setClassForTemplateLoading(FreeMarkerManager.class, "/templates");
                FREE_MARKER_CONFIG.setTemplateLoader(new ClassTemplateLoader(FreeMarkerManager.class, "/templates"));
                FREE_MARKER_CONFIG.setDefaultEncoding("UTF-8");
                FREE_MARKER_CONFIG.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
                FREE_MARKER_CONFIG.setLogTemplateExceptions(false);
                FREE_MARKER_CONFIG.setWrapUncheckedExceptions(true);
                FREE_MARKER_CONFIG.setFallbackOnNullLoopVariable(false);
            } catch (Exception e) {
                log.error(ExceptionUtils.getStackTrace(e));
            }
        }
    }
}
