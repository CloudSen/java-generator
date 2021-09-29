package com.yangyunsen.generator.java.common.mapping;

import com.yangyunsen.generator.java.common.model.enums.Mode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 不同类对应的模板映射
 *
 * @author clouds3n
 * @date 2021-09-29
 */
@Getter
@RequiredArgsConstructor
public enum JpaTemplateMapping implements TemplateMapping {

    //<editor-fold desc="common">
    /**
     * dto.ftl
     */
    DTO(List.of(Mode.JPA, Mode.MP), "dto.ftl"),
    /**
     * controller.ftl
     */
    CONTROLLER(List.of(Mode.JPA), "controller.ftl"),
    //</editor-fold>

    //<editor-fold desc="jpa">
    /**
     * jpa-entity.ftl
     */
    JPA_ENTITY(List.of(Mode.JPA), "jpa-entity.ftl"),
    /**
     * jpa-repo.ftl
     */
    JPA_REPOSITORY(List.of(Mode.JPA), "jpa-repo.ftl"),
    /**
     * jpa-custom-repo.ftl
     */
    JPA_CUSTOM_REPOSITORY(List.of(Mode.JPA), "jpa-custom-repo.ftl"),
    /**
     * jpa-service.ftl
     */
    JPA_SERVICE(List.of(Mode.JPA), "jpa-service.ftl"),
    /**
     * jpa-service-impl.ftl
     */
    JPA_SERVICE_IMPL(List.of(Mode.JPA), "jpa-service-impl.ftl"),
    //</editor-fold>

    //<editor-fold desc="mybatis">
    /**
     * mp-entity.ftl
     */
    MP_ENTITY(List.of(Mode.JPA), "mp-entity.ftl"),
    /**
     * mp-repo.ftl
     */
    MP_REPOSITORY(List.of(Mode.JPA), "mp-repo.ftl"),
    /**
     * mp-service.ftl
     */
    MP_SERVICE(List.of(Mode.JPA), "mp-service.ftl"),
    /**
     * mp-service-impl.ftl
     */
    MP_SERVICE_IMPL(List.of(Mode.JPA), "mp-service-impl.ftl"),
    //</editor-fold>
    ;

    private final List<Mode> modes;
    private final String templateName;
}
