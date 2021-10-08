package com.yangyunsen.generator.java.common.mapping;

import com.yangyunsen.generator.java.common.GeneratorException;
import com.yangyunsen.generator.java.common.model.enums.Mode;
import com.yangyunsen.generator.java.common.model.enums.MvcLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * 不同类对应的模板映射
 *
 * @author clouds3n
 * @date 2021-09-29
 */
@Getter
@RequiredArgsConstructor
public enum DefaultTemplateMapping implements TemplateMapping {

    //<editor-fold desc="common">
    /**
     * dto.ftl
     */
    DTO(List.of(Mode.JPA, Mode.MP), MvcLevel.DTO, "dto.ftl"),
    /**
     * controller.ftl
     */
    CONTROLLER(List.of(Mode.JPA, Mode.MP), MvcLevel.CONTROLLER, "controller.ftl"),
    /**
     * service.ftl
     */
    SERVICE(List.of(Mode.JPA, Mode.MP), MvcLevel.SERVICE, "service.ftl"),
    /**
     * service-impl.ftl
     */
    JPA_SERVICE_IMPL(List.of(Mode.JPA, Mode.MP), MvcLevel.SERVICE_IMPL, "service-impl.ftl"),
    //</editor-fold>

    //<editor-fold desc="jpa">
    /**
     * jpa-entity.ftl
     */
    JPA_ENTITY(List.of(Mode.JPA), MvcLevel.ENTITY, "jpa-entity.ftl"),
    /**
     * jpa-repo.ftl
     */
    JPA_REPOSITORY(List.of(Mode.JPA), MvcLevel.REPO, "jpa-repo.ftl"),
    /**
     * jpa-custom-repo.ftl
     */
    JPA_CUSTOM_REPOSITORY(List.of(Mode.JPA), MvcLevel.CUSTOM_REPO, "jpa-custom-repo.ftl"),
    /**
     * jpa-custom-repo-impl.ftl
     */
    JPA_CUSTOM_REPOSITORY_IMPL(List.of(Mode.JPA), MvcLevel.CUSTOM_REPO_IMPL, "jpa-custom-repo-impl.ftl"),
    //</editor-fold>

    //<editor-fold desc="mybatis">
    /**
     * mp-entity.ftl
     */
    MP_ENTITY(List.of(Mode.MP), MvcLevel.ENTITY, "mp-entity.ftl"),
    /**
     * mp-repo.ftl
     */
    MP_REPOSITORY(List.of(Mode.MP), MvcLevel.REPO, "mp-repo.ftl"),
    //</editor-fold>
    ;

    private final List<Mode> modes;
    private final MvcLevel mvcLevel;
    private final String templateName;

    public static String getTemplateName(Mode mode, MvcLevel mvcLevel) {
        return Arrays.stream(values())
            .filter(m -> m.modes.contains(mode) && m.mvcLevel == mvcLevel)
            .findFirst()
            .map(DefaultTemplateMapping::getTemplateName)
            .orElseThrow(() -> new GeneratorException("不支持的类型: " + mode + "," + mvcLevel));
    }
}
