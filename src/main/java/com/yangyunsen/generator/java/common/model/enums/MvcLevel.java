package com.yangyunsen.generator.java.common.model.enums;

/**
 * MVC层次类型
 *
 * @author CloudS3n
 * @date 2021-09-30 11:59
 */
public enum MvcLevel {

    /**
     * controller层
     */
    CONTROLLER,
    /**
     * service层
     */
    SERVICE,
    /**
     * service实现层
     */
    SERVICE_IMPL,
    /**
     * repo层
     */
    REPO,
    /**
     * repo实现层
     */
    REPO_IMPL,
    /**
     * 自定义repo层
     */
    CUSTOM_REPO,
    /**
     * 自定义repo实现层
     */
    CUSTOM_REPO_IMPL,
    /**
     * ORM实体
     */
    ENTITY,
    /**
     * 数据传输实体
     */
    DTO
}
