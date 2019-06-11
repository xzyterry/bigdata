package com.jawnho.douyuspringboot.config.datax.Mysql2Hive;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;


public class Mysql2HiveReader {

    private JSONObject reader;

    public Mysql2HiveReader() {
    }

    public Mysql2HiveReader(JSONObject reader) {
        this.reader = reader;
    }


    public JSONObject setParameter(Map<String, Object> map) {
        JSONObject parameter = reader.getJSONObject("parameter");
        parameter.put("username", map.get("username"));
        parameter.put("password", map.get("password"));
        parameter.put("connection", map.get("connection"));
        parameter.put("column", map.get("column"));
        parameter.put("writeMode", map.get("writeMode"));
        parameter.put("splitPk", map.get("splitPk"));
        reader.put("parameter", parameter);
        return reader;
    }

    @Override
    public String toString() {

        return reader.toString();
    }
}
