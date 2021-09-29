package com.yangyunsen.generator.java.common.model.dto;

import com.yangyunsen.generator.java.common.model.enums.JdbcDriverPkgName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 通用数据库连接信息
 *
 * @author clouds3n
 * @date 2021-09-23
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseInfo implements Serializable {
    private String url;
    private String username;
    private String passwd;
    private JdbcDriverPkgName driverPkgName;
}
