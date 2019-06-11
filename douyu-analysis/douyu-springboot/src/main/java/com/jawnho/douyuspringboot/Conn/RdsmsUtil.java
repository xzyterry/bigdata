package com.jawnho.douyuspringboot.Conn;

import com.jawnho.douyuspringboot.entity.model.DsInfo;
import com.jawnho.douyuspringboot.response.JDBCStatus;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

@Slf4j
public class RdsmsUtil {


    public static JDBCStatus getConn(DsInfo model) {

        return getConn(model.getUrl(), model.getDriver(), model.getUsername(), model.getPassword());
    }


    public static JDBCStatus getConn(String url, String driver, String username, String password) {

        String driverName = "";

        switch (driver) {
            case "oracel": {
                url = "jdbc:oracle:thin:@//" + url;
            }
            driverName = "oracle.jdbc.driver.OracleDriver";
            break;
            default: {
                url = "jdbc:mysql://" + url
                        + "?useUnicode=true&useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC";
                driverName = "com.mysql.jdbc.Driver";
            }
        }

        Connection conn = null;
        String message = "";
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            log.error(e.getMessage());
            message = e.getMessage();
        }

        return new JDBCStatus(message, conn, null);
    }


    public static Map<String, Object> runSql(Connection conn, String sql) {

        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            List<String> colNames = new ArrayList<>();

            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                colNames.add(metaData.getColumnLabel(i));
            }

            while (resultSet.next()) {
                Map<String, Object> tmp = new HashMap<>();
                for (String colName : colNames)
                    tmp.put(colName, resultSet.getObject(colName));
                list.add(tmp);
            }
            Map<String, Object> map = new HashMap<>();
            map.put("data", list);
            result = map;
        } catch (SQLException e) {
            result.put("error", e.getMessage());
        }

        return result;
    }

    public static List<List<String>> getResultSet(Connection conn, String sql) {

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<List<String>> lists = new ArrayList<>();
        List<String> fields = new ArrayList<>();
        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();

            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                fields.add(metaData.getColumnLabel(i));
            }
            lists.add(fields);
            int limit = 1;
            while (resultSet.next() && limit < 1000000) {
                List<String> data = new ArrayList<>();
                for (String field : fields) {
                    Object object = resultSet.getObject(field);
                    String dt = "";
                    if (object instanceof String) {
                        dt = resultSet.getString(field);
                    } else if (object instanceof Date) {
                        dt = sd.format(resultSet.getDate(field));
                    } else if (object instanceof Long) {
                        dt = String.valueOf(resultSet.getLong(field));
                    } else if (object instanceof Double) {
                        dt = String.valueOf(resultSet.getDouble(field));
                    } else if (object instanceof Boolean) {
                        dt = String.valueOf(resultSet.getBoolean(field));
                    } else if (object instanceof byte[]) {
                        dt = String.valueOf(resultSet.getBytes(field));
                    } else if (object instanceof BigDecimal) {
                        dt = String.valueOf(resultSet.getBytes(field));
                    } else {
                        if (object != null)
                            dt = resultSet.getObject(field).toString();
                    }
                    data.add(dt);
                }
                lists.add(data);
                limit++;
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return lists;
    }

    public static List<List<Object>> getResultSet2(Connection conn, String sql) {

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<List<Object>> lists = new ArrayList<>();
        List<String> fields = new ArrayList<>();
        try {

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();

            List<Object> tmp = new ArrayList<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                fields.add(metaData.getColumnLabel(i));
                tmp.add(metaData.getColumnLabel(i));
            }
            lists.add(tmp);

            int limit = 1;
            while (resultSet.next() && limit < 1000000) {
                List<Object> data = new ArrayList<>();
                for (String field : fields) {
                    Object object = resultSet.getObject(field);
                    data.add(object);
                }
                lists.add(data);
                limit++;
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return lists;
    }

}
