package com.jawnho.douyuspringboot.hive;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理工具类
 */
public class AbnormalUtils {

    public static Object getAbnormal(Exception e) {
        Object abnormalType = e.getCause().getClass().toString();
        Object abnormalName = e.getCause().getMessage().toString();
        Map<String, Object> map = new HashMap<>();
        map.put("异常类型", abnormalType);
        map.put("异常点信息", abnormalName);
        return map.toString();
    }

}