package com.jawnho.douyuspringboot.util;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResultUtils {

    public static String toJson(Object obj) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", obj);
        return JSONObject.toJSONString(result);
    }
}
