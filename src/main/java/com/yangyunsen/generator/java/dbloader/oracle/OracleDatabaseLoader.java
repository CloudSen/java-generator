package com.yangyunsen.generator.java.dbloader.oracle;

import cn.hutool.core.collection.CollectionUtil;
import com.yangyunsen.generator.java.common.GeneratorException;
import com.yangyunsen.generator.java.dbloader.DatabaseLoader;
import com.yangyunsen.generator.java.dbloader.module.DatabaseInfo;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Oracle 数据库信息加载器
 *
 * @author clouds3n
 * @date 2021-09-23
 */
@AllArgsConstructor
public class OracleDatabaseLoader implements DatabaseLoader {

    private DatabaseInfo databaseInfo;

    private static final String GET_TABLE_COLUMNS_SQL = "SELECT\n" +
            "    t.TABLE_NAME \"tableName\",\n" +
            "    t.COLUMN_NAME \"columnName\",\n" +
            "    t.DATA_TYPE \"dataType\",\n" +
            "    t.DATA_LENGTH \"dataLength\",\n" +
            "    t.DATA_PRECISION \"dataPrecision\",\n" +
            "    t.DATA_SCALE \"dataScale\"\n" +
            "FROM USER_TAB_COLUMNS t\n" +
            "WHERE t.TABLE_NAME IN (?)";

    private static final String GET_TABLE_PRIMARY_KEY_SQL = "SELECT\n" +
            "    cols.TABLE_NAME \"tableName\",\n" +
            "    cols.COLUMN_NAME \"pkColumnName\"\n" +
            "FROM ALL_CONSTRAINTS cons, ALL_CONS_COLUMNS cols\n" +
            "WHERE cols.TABLE_NAME IN (?)\n" +
            "AND cons.CONSTRAINT_TYPE = 'p'\n" +
            "AND cons.OWNER = ?\n" +
            "AND cons.CONSTRAINT_NAME = cols.CONSTRAINT_NAME\n" +
            "AND cons.OWNER = cols.OWNER\n" +
            "ORDER BY cols.TABLE_NAME, cols.position";

    @Override
    public Map<String, List<OracleColumnInfo>> getMultiTableInfo(List<String> tableNames) {
        boolean invalidParams = StringUtils.isBlank(databaseInfo.getUsername()) || CollectionUtil.isEmpty(tableNames);
        if (invalidParams) {
            throw new GeneratorException("用户名或表名列表为空");
        }
        String placeHolders = String.join(",", Collections.nCopies(tableNames.size(), "'?'"));
        String getTablePkSql = GET_TABLE_PRIMARY_KEY_SQL.replace("(?)", "(" + placeHolders + ")");
        String getTableColumnsSql = GET_TABLE_COLUMNS_SQL.replace("(?)", "(" + placeHolders + ")");
        try (Connection connection = getJdbcConnection(databaseInfo)) {
            Map<String, String> pkColumnMap = this.getTablePrimaryKeyMap(
                    connection,
                    getTablePkSql,
                    databaseInfo.getUsername(),
                    tableNames
            );
            return this.getColumnNameAndType(connection, getTableColumnsSql, tableNames, pkColumnMap)
                    .parallelStream().collect(Collectors.groupingBy(OracleColumnInfo::getTableName));
        } catch (SQLException sqlException) {
            throw new GeneratorException("数据库执行异常", sqlException);
        } catch (ClassNotFoundException classNotFoundException) {
            throw new GeneratorException("获取数据库驱动异常", classNotFoundException);
        }
    }

    @Override
    public Map<String, String> getTablePrimaryKeyMap(Connection connection, String sql, String username, List<String> tableNames) throws SQLException {
        Map<String, String> pkColumnMap = new HashMap<>(16);
        try (PreparedStatement pkColumnPs = connection.prepareStatement(sql)) {
            int i = 1;
            for (String tableName : tableNames) {
                pkColumnPs.setString(i, tableName);
                i++;
            }
            pkColumnPs.setString(i, username);
            try (ResultSet pkColumnRs = pkColumnPs.executeQuery()) {
                while (pkColumnRs.next()) {
                    pkColumnMap.put(pkColumnRs.getString("tableName"), pkColumnRs.getString("pkColumnName"));
                }
            }
        }
        return pkColumnMap;
    }

    @Override
    public List<OracleColumnInfo> getColumnNameAndType(Connection connection, String sql, List<String> tableNames, Map<String, String> pkColumnMap) throws SQLException {
        List<OracleColumnInfo> resultList = new ArrayList<>();
        OracleColumnInfo oracleColumnInfo;
        try (PreparedStatement tableColumnPs = connection.prepareStatement(sql)) {
            for (int i = 1; i <= tableNames.size(); i++) {
                tableColumnPs.setString(i, tableNames.get(i - 1));
            }
            try (ResultSet tableColumnRs = tableColumnPs.executeQuery()) {
                while (tableColumnRs.next()) {
                    oracleColumnInfo = new OracleColumnInfo()
                            .setTableName(tableColumnRs.getString("tableName"))
                            .setColumnName(tableColumnRs.getString("columnName"))
                            .setDataType(tableColumnRs.getString("dataType"))
                            .setDataLength(tableColumnRs.getInt("dataLength"))
                            .setDataPrecision(tableColumnRs.getInt("dataPrecision"))
                            .setDataScale(tableColumnRs.getInt("dataScale"));
                    String pkColumnName = pkColumnMap.get(oracleColumnInfo.getTableName());
                    if (StringUtils.equals(pkColumnName, oracleColumnInfo.getColumnName())) {
                        oracleColumnInfo.setPkFlag(Boolean.TRUE);
                    }
                    resultList.add(oracleColumnInfo);
                }
            }
        }
        return resultList;
    }
}
