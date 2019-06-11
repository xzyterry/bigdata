package com.jawnho.douyuspringboot.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class BeanMapUtil {

    public static Properties beanToProperties(Object obj) throws IllegalAccessException {

        Properties p = new Properties();
        Map<String, Object> map = objectToMap(obj);
        p.putAll(map);
        return p;
    }

    public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) throws IllegalAccessException, InstantiationException {
        if (map == null)
            return null;
        T obj = beanClass.newInstance();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }

            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }

        return obj;
    }

    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }

}

