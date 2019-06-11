package com.jawnho.douyuspringboot.hive;

import com.jawnho.douyuspringboot.entity.model.TbField;
import com.jawnho.douyuspringboot.response.JDBCStatus;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Hive JDBC操作
 */

@Slf4j
public class HiveJDBC {

    private static Connection conn = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    /**
     * 加载驱动,创建连接
     *
     * @throws Exception
     */
    private static void init() {
        try {
            Class.forName(PlatformDictionary.DRIVER_NAME);
            conn = DriverManager.getConnection(PlatformDictionary.URL, PlatformDictionary.USER_NAME,
                    PlatformDictionary.PASSWORD);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            Object object = AbnormalUtils.getAbnormal(e);
            log.error(object.toString());
            log.error(object.toString());

        }
    }

    /**
     * 创建数据库
     *
     * @param databaseName
     */
    public static void createDatabase(String databaseName) {
        try {
            init();

            String sql = "create database " + databaseName;
            log.info("Running: " + sql);

            stmt.execute(sql);
        } catch (SQLException e) {
            Object object = AbnormalUtils.getAbnormal(e);
            log.error(object.toString());
        } finally {
            destory();
        }
    }

    /**
     * 查询所有数据库
     *
     * @return
     */
    public static List<String> showDatabases() {
        List<String> list = new ArrayList<>();
        try {
            init();

            String sql = "show databases";
            log.info("Running: " + sql);

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getString(1));
            }

        } catch (SQLException e) {
            Object object = AbnormalUtils.getAbnormal(e);
            log.error(object.toString());
        } finally {
            destory();
        }
        return list;
    }

    /**
     * 删除数据库
     *
     * @param databaseName
     */
    public static void dropDatabase(String databaseName) {
        try {
            init();

            String sql = "drop database if exists " + databaseName;
            log.info("Running: " + sql);

            stmt.execute(sql);
        } catch (SQLException e) {
            Object object = AbnormalUtils.getAbnormal(e);
            log.error(object.toString());
        } finally {
            destory();
        }
    }

    /**
     * 创建表
     *
     * @param createTableSql
     */
    public static JDBCStatus createTable(String createTableSql) {
        String message = "";
        try {
            init();

            log.info("Running: " + createTableSql);

            stmt.execute(createTableSql);
        } catch (SQLException e) {
            Object object = AbnormalUtils.getAbnormal(e);
            log.error(object.toString());
            message = object.toString();
        } finally {
            destory();
        }
        return new JDBCStatus(message, null);

    }

    /**
     * 查询所有表
     *
     * @return
     */
    public static List<String> showTables(String databaseName) {
        List<String> list = new ArrayList<>();
        try {
            init();

            String useSql = "use " + databaseName;
            log.info("Running: " + useSql);
            stmt.execute(useSql);

            String sql = "show tables";
            log.info("Running: " + sql);

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(rs.getString(1));
            }

        } catch (SQLException e) {
            Object object = AbnormalUtils.getAbnormal(e);
            log.error(object.toString());
        } finally {
            destory();
        }
        return list;
    }

    /**
     * 查看表结构
     *
     * @param databaseName
     * @param tableName
     * @return
     */
    public static List<Map<String, Object>> descTable(String databaseName, String tableName) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = null;

        try {
            init();

            String sql = "desc " + databaseName + "." + tableName;
            log.info("Running: " + sql);

            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                map = new HashMap<>();
                map.put("colName", rs.getString(1));
                map.put("dataType", rs.getString(2));
                list.add(map);
            }

        } catch (SQLException e) {
            Object object = AbnormalUtils.getAbnormal(e);
            log.error(object.toString());
        } finally {
            destory();
        }
        return list;
    }

    /**
     * 加载数据
     *
     * @param hdfsPath
     * @param tableName
     */
    public static void loadData(String hdfsPath, String tableName) {
        try {
            init();

            String sql = "load data inpath '" + hdfsPath + "' insert into table " + tableName;
            log.info("Running: " + sql);

            stmt.execute(sql);
        } catch (SQLException e) {
            Object object = AbnormalUtils.getAbnormal(e);
            log.error(object.toString());
        } finally {
            destory();
        }
    }

    /**
     * 查询数据
     *
     * @param selectSql
     * @return
     */
    public static List<String> selectData(String selectSql) {
        List<String> list = new ArrayList<>();
        try {
            init();

            log.info("Running: " + selectSql);

            rs = stmt.executeQuery(selectSql);
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException e) {
            Object object = AbnormalUtils.getAbnormal(e);
            log.error(object.toString());
        } finally {
            destory();
        }
        return list;
    }

    /**
     * 查询数据
     *
     * @param selectSql
     * @return
     */
    public static JDBCStatus query(String selectSql) {

        String exception = "";
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            init();

            log.info("Running: " + selectSql);

            rs = stmt.executeQuery(selectSql);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                for (int c = 1; c <= columnCount; c++) {
                    map.put(metaData.getColumnName(c), rs.getString(c));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            Object object = AbnormalUtils.getAbnormal(e);
            exception = object.toString();
//            log.error(object.toString());
        } finally {
            destory();

        }
        return new JDBCStatus(exception, list);
    }

    /**
     * 删除数据库表
     *
     * @param databaseName
     * @param tableName
     */
    public static void dropTable(String databaseName, String tableName) {
        try {
            init();

            String sql = "drop table if exists " + databaseName + "." + tableName;
            log.info("Running: " + sql);

            stmt.execute(sql);
        } catch (SQLException e) {
            Object object = AbnormalUtils.getAbnormal(e);
            log.error(object.toString());
        } finally {
            destory();
        }
    }

    /**
     * 释放资源
     */
    private static void destory() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            Object object = AbnormalUtils.getAbnormal(e);
            log.error(object.toString());
        }
    }


    public static JDBCStatus getFieldsByTb_id(String tb_id) {

        String exception = "";
        List<TbField> list = new ArrayList<>();
        try {
            init();
            log.info("Running: desc " + tb_id);

            rs = stmt.executeQuery("desc " + tb_id);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {

                TbField tbField = TbField.builder()
                        .fid("")
                        .fname(rs.getString(1))
                        .ftype(rs.getString(2))
                        .fcomment(rs.getString(3))
                        .build();

                list.add(tbField);
            }
        } catch (SQLException e) {
            Object object = AbnormalUtils.getAbnormal(e);
            exception = object.toString();
        } finally {
            destory();

        }
        return new JDBCStatus(exception, list, null);
    }

    public static void main(String[] argus){
//        getFieldsByTb_id("student2");

        query("desc student2").getResult().forEach(a->{
            a.forEach((x,y)->System.out.println(x+":"+y));
        });
    }


}