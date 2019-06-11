package com.jawnho.douyuspringboot.config.datax.Mysql2Hive;


import lombok.Builder;
import lombok.Data;

/**
 * "parameter": {
 * "password": "123456",
 * "column": ["word","cnt"],
 * "connection": [{ "jdbcUrl": ["jdbc:mysql://192.168.10.116:3306/test_datax"],"table": ["test_table"]}],
 * "writeMode": "insert",
 * "splitPk": "cnt",
 * "username": "root"*
 * },
 */

@Data
@Builder
public class ReaderParameter {

    private String username;
    private String password;
    private String writeMode;
    private String splitPk;
    private String[] column;
    private ReaderConn[] connection;


}

@Data
@Builder
class ReaderConn{
    private String[] jdbcUrl;
    private String[] table;
}


