package com.jawnho.douyuspringboot.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class JDBCStatus {

    private List<Map<String, Object>> result;
    private String exception;
    private Object object;

    public JDBCStatus() {
    }

    public JDBCStatus(String exception, List<Map<String, Object>> result) {
        this.exception = exception;
        this.result = result;
    }

    public JDBCStatus(String exception,Object object, List<Map<String, Object>> result) {
        this.exception = exception;
        this.object = object;
        this.result = result;
    }

}
