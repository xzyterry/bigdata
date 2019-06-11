package com.jawnho.douyuspringboot.response;

import java.io.Serializable;


public class ResultEntity<T> implements Serializable {
    private int code = 0;
    private String message = "";
    private T result = null;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public ResultEntity(T result) {
        this.result = result;
    }

    public ResultEntity(int code, String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public ResultEntity(int code, T result) {
        switch (code) {
            case 0:
                message = "success";
                break;
            case 500:
                message = "failed";
                break;
            default:
                message = "undefined";
        }
        this.result = result;
    }

    public ResultEntity() {

    }
}
