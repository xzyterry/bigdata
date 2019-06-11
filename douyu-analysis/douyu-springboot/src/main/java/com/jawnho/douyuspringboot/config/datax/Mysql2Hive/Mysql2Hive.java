package com.jawnho.douyuspringboot.config.datax.Mysql2Hive;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class Mysql2Hive {

    private JSONObject config;

    public Mysql2Hive(){}

    public Mysql2Hive(String json){
        this.config = JSONObject.parseObject(json);
    }

    /**
     * "parameter": {
     * 				"password": "123456",
     * 				"column": ["word",
     * 				"cnt"],
     * 				"connection": [{
     * 					"jdbcUrl": ["jdbc:mysql://192.168.10.116:3306/test_datax"],
     * 					"table": ["test_table"]
     *                                }],
     * 				"writeMode": "insert",
     * 				"splitPk": "cnt",
     * 				"username": "root"* 			},
     * @param map
     */
    public void setParameter(Map<String,Object> map){
//        this.config.getJSONObject("")
    }

    @Override
    public String toString() {

        return config.toString();
    }


}
