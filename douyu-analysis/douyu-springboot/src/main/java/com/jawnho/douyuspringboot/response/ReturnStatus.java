package com.jawnho.douyuspringboot.response;

public enum ReturnStatus {

    /**
     * 0 成功
     * 1XXX 用户登录
     * 2XXX 参数验证
     * 3XXX 业务层
     * 4XXX 其他异常(数据上传)
     * 5XXX 系统异常
     */

    PARAM_FIELD_INCORRECT(2001,"参数异常"),
    DB_REFLACT_ERROR(2002,"数据库实体字段映射异常"),
    REQUEST_NOT_FOUND(2003, "请求没发现"),

    ACCOUNT_CONNECT_ERROR(3001, "账号连接失败"),
    JOB_DETAIL_IS_NOT_EXIST(3002, "jobDetailId 不存在"),
    GROUPNAME_JOBNAME_IS_EXIST(3003, "groupName和jobName已存在"),

    UNKNOWN_ERROR(5555,"未知异常");

    private int code;
    private String message;

    private ReturnStatus(int code, String message){
        this.code = code;
        this.message = message;
    }


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
}
