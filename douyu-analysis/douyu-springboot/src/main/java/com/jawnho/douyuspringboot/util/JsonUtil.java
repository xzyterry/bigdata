package com.jawnho.douyuspringboot.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class JsonUtil {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);

        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * 对象 -> Json
     * @param obj
     */
    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Json -> 对象
     */
    public static <T> T toBean(String json, Class<T> classType) {
        try {
            return mapper.readValue(json, classType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T toBean(String json, Class<T> contextClass, Class<?>... parameterClasses) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametrizedType(contextClass, contextClass, parameterClasses);
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> toList(String json, Class<T> classType) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametrizedType(ArrayList.class, List.class, classType);
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <K, V> Map<K, V> toMap(String json, Class<K> keyClassType, Class<V> valueClassType) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametrizedType(HashMap.class, Map.class, keyClassType, valueClassType);
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
