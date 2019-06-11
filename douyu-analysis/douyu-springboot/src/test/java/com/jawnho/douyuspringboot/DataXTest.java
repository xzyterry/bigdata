package com.jawnho.douyuspringboot;

import com.alibaba.fastjson.JSONObject;
import com.jawnho.douyuspringboot.util.FileUtil;

import java.util.Map;

public class DataXTest {
    //json 解析配置

    public static void main(String[] argus) {

        String pathname = "C:\\Users\\Administrator\\Desktop\\mysql2hive.json";

        String json = FileUtil.readFile(pathname);
//        Mysql2Hive mysql2Hive = JsonUtil.toBean(json, Mysql2Hive.class);
//        System.out.println(mysql2Hive.getJob());

        //设置channel
        JSONObject map = JSONObject.parseObject(json);

        JSONObject job = map.getJSONObject("job");
        JSONObject setting  = job.getJSONObject("setting");
        JSONObject speed = setting.getJSONObject("speed");

        speed.put("channel",4);
        setting.put("speed",speed);
        job.put("setting",setting);

        System.out.println(JSONObject.toJSONString(job));

    }




}

