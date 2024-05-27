package com.zzh.dream.study.base.utils;

import cn.hutool.core.util.ObjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据同步的工具类（从数据库A，批量同步表a,b,c的数据到，数据库B）
 * 注意：开始同步数据之前会先清空目标表的数据
 */
public class DataSyncService {

    private final JdbcTemplate resourceJdbcTemplate;
    private final JdbcTemplate targetJdbcTemplate;

    private PlatformTransactionManager transactionManager;

    public DataSyncService(DataSource resourceDataSource, DataSource targetDataSource) {
        this.resourceJdbcTemplate = new JdbcTemplate(resourceDataSource);
        this.targetJdbcTemplate = new JdbcTemplate(targetDataSource);
        this.transactionManager = new DataSourceTransactionManager(targetDataSource);
    }

    public void syncData(List<String> tableNames) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            for (String tableName : tableNames) {
                //先删除目标表中的数据
                targetJdbcTemplate.execute("DELETE FROM " + tableName);
                try {
                    this.syncSingleTable(tableName);
                } catch (Exception e) {
                    throw new RuntimeException("Error syncing table " + tableName, e);
                }
            }
            transactionManager.commit(status);
            System.out.println("Data synchronization completed and committed.");
        } catch (Exception e) {
            transactionManager.rollback(status);
            System.out.println("An error occurred. Data synchronization rolled back.");
            e.printStackTrace();
        }
    }

    private void syncSingleTable(String tableName) throws SQLException {
        List<String> targetColumns = getTargetTableColumnNames(tableName);
        List<Map<String, Object>> dbARecords = resourceJdbcTemplate.query("SELECT * FROM " + tableName, (rs, rowNum) -> {
            Map<String, Object> record = new HashMap<>();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                record.put(rs.getMetaData().getColumnName(i), rs.getObject(i));
            }
            return record;
        });

        for (Map<String, Object> dbARecord : dbARecords) {
            List<String> nonEmptyKeys = dbARecord.entrySet().stream()
                    .filter(entry -> targetColumns.contains(entry.getKey()) && ObjectUtil.isNotNull(entry.getValue()))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            String columnNames = StringUtils.join(nonEmptyKeys, ", ");
            String placeholders = StringUtils.repeat("?, ", nonEmptyKeys.size()).substring(0, nonEmptyKeys.size() * 3 - 2); // 去掉最后一个逗号和空格

            String insertSql = "INSERT INTO " + tableName + " (" + columnNames + ") VALUES (" + placeholders + ")";
            List<Object> values = nonEmptyKeys.stream().map(dbARecord::get).collect(Collectors.toList());
            targetJdbcTemplate.update(insertSql, values.toArray());
        }
    }

    private List<String> getTargetTableColumnNames(String tableName) throws SQLException {
        List<String> columnNames = new ArrayList<>();
        ResultSet columns = targetJdbcTemplate.getDataSource().getConnection().getMetaData().getColumns(null, null, tableName, null);
        while (columns.next()) {
            columnNames.add(columns.getString("COLUMN_NAME"));
        }
        columns.close();
        return columnNames;
    }

    public static DataSource createDataSource(String url, String username, String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(DRIVER_NAME);
        return dataSource;
    }

    public static final String DRIVER_NAME = "org.postgresql.Driver";

    public static final String DB_A_URL = "jdbc:postgresql://ip:port/database";
    public static final String DB_A_USERNAME = "user";
    public static final String DB_A_PASSWORD = "password";

    public static final String DB_B_URL = "jdbc:postgresql://ip:port/database";
    public static final String DB_B_USERNAME = "user";
    public static final String DB_B_PASSWORD = "password";

    private static List<String> tablesToSync = Arrays.asList("table_name");

    public static void main(String[] args) {
        // 创建数据源
        DataSource resourceDataSource = createDataSource(DB_A_URL, DB_A_USERNAME, DB_A_PASSWORD);
        DataSource targetDataSource = createDataSource(DB_B_URL, DB_B_USERNAME, DB_B_PASSWORD);
        // 创建 DataSyncService 并注入数据源
        DataSyncService dataSyncService = new DataSyncService(resourceDataSource, targetDataSource);

        // 同步数据
        dataSyncService.syncData(tablesToSync);

        System.out.println("Data synchronization completed.");
    }


}

