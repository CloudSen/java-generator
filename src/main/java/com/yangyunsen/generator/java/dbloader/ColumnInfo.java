package com.yangyunsen.generator.java.dbloader;

/**
 * 表示数据库表字段信息
 *
 * @author clouds3n
 * @date 2021-09-24
 */
public interface ColumnInfo {

    /**
     * 获取表名
     *
     * @return 表名
     */
    String getTableName();

    /**
     * 获取字段名
     *
     * @return 字段名
     */
    String getColumnName();

    /**
     * 是否为主键
     *
     * @return true是主键，false或null不是主键
     */
    Boolean getPkFlag();
}
