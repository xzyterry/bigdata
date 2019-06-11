package com.jawnho.douyuspringboot.response.exception;


import com.jawnho.douyuspringboot.response.ReturnStatus;

/**
 *
 * @date 2018-09-12
 * @description
 *     业务层
 *     3XXX
 */
public class BussinessException extends RuntimeException {

    private int code;

    public BussinessException(int code, String message){
        super(message);
        this.code = code;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BussinessException(ReturnStatus returnStatus){
        super(returnStatus.getMessage());
        this.code = returnStatus.getCode();
    }
}
