package com.yangyunsen.generator.java.common.model.dto;

import com.yangyunsen.generator.java.common.model.enums.Mode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 生成器配置
 *
 * @author clouds3n
 * @date 2021-09-29
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class GeneratorConfig {

    private Mode mode;
    private PackageInfo packageInfo;
    private DatabaseInfo databaseInfo;
}