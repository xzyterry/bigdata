package com.jawnho.douyuspringboot.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static SimpleDateFormat sd = null;
    public static String defaultFormat = "YYYY-MM-dd";
    public static String defaultFormat2 = "YYYY-MM-dd HH:mm:ss";

    public static String getTime(){
        sd = new SimpleDateFormat(defaultFormat2);
        return sd.format(new Date());
    }

    public static String getTime2(){
        sd = new SimpleDateFormat(defaultFormat);
        return sd.format(new Date());
    }

    public static String getTime(String format) {
        sd = new SimpleDateFormat(format);
        return sd.format(new Date());
    }
    /**
     * 得到当前时间戳
     *
     * @return
     */
    public static Long getCurrentTimeStamp() {
        long timeMillis = System.currentTimeMillis();
        return timeMillis;
    }
}
