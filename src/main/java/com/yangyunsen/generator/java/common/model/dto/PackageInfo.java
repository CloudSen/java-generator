package com.yangyunsen.generator.java.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 通用类路径信息
 *
 * @author clouds3n
 * @date 2021-09-23
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PackageInfo implements Serializable {

    private String parentPackage;
    private String childPackage;
}