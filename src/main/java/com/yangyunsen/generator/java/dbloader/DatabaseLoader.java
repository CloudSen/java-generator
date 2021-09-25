package com.yangyunsen.generator.java.dbloader;

import com.yangyunsen.generator.java.dbloader.module.DatabaseInfo;
import com.yangyunsen.generator.java.dbloader.oracle.OracleColumnInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 表示数据库信息加载器
 *
 * @author clouds3n
 * @date 2021-09-23
 */
public interface DatabaseLoader {

    /**
     * 获取JDBC连接对象
     *
     * @param databaseInfo 数据库连接信息
     * @return jdbc connection
     * @throws ClassNotFoundException 数据库驱动未找到
     * @throws SQLException           获取JDBC连接失败
     */
    default Connection getJdbcConnection(DatabaseInfo databaseInfo) throws ClassNotFoundException, SQLException {
        Class.forName(databaseInfo.getDriverClassName().getDriverName());
        return DriverManager.getConnection(databaseInfo.getUrl(), databaseInfo.getUsername(), databaseInfo.getPasswd());
    }

    /**
     * 批量获取表信息
     *
     * @param tableNames 表名（大小写敏感）
     * @return 表信息 key: 表名  value: 表字段信息
     */
    Map<String, List<OracleColumnInfo>> getMultiTableInfo(List<String> tableNames);

    /**
     * 获取表主键信息MAP
     *
     * @param connection 数据库连接对象
     * @param sql        查询sql
     * @param username   数据库连接用户名
     * @param tableNames 多个表名
     * @return 表主键信息MAP key: 表名 value: 主键名
     * @throws SQLException sql执行错误
     */
    Map<String, String> getTablePrimaryKeyMap(Connection connection, String sql,
                                              String username, List<String> tableNames)
        throws SQLException;

    /**
     * 获取表字段名和字段类型等信息
     *
     * @param connection  数据库连接对象
     * @param sql         查询sql
     * @param tableNames  多个表名
     * @param pkColumnMap 表主键信息map
     * @return 字段富信息
     * @throws SQLException sql执行错误
     */
    List<OracleColumnInfo> getColumnNameAndType(Connection connection, String sql,
                                                List<String> tableNames, Map<String, String> pkColumnMap)
        throws SQLException;
}
