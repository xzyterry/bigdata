package com.jawnho.douyuspringboot.response;

import lombok.Data;

@Data
public class DaoStatus {
    private String exception;
    private Object object;

    public DaoStatus() {
    }

    public DaoStatus(String exception, Object o) {
        this.exception = exception;
        this.object = o;
    }

}
