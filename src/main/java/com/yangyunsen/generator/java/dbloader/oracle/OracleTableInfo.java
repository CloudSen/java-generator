package com.yangyunsen.generator.java.dbloader.oracle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Oracle 表信息
 *
 * @author clouds3n
 * @date 2021-09-23
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class OracleTableInfo implements Serializable {

    private String tableName;
    private String columnName;
    private String dataType;
    private String dataLength;
    private String dataPrecision;
    private String dataScale;
}
