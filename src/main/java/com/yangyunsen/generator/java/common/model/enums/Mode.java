package com.yangyunsen.generator.java.common.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 生成器模式
 *
 * @author clouds3n
 * @date 2021-09-29
 */
@Getter
@RequiredArgsConstructor
public enum Mode {

    /**
     * JPA模式
     */
    JPA,
    /**
     * MYBATIS_PLUS模式
     */
    MP,
    
}
