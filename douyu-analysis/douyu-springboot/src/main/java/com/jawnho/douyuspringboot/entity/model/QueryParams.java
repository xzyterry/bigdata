package com.jawnho.douyuspringboot.entity.model;

import lombok.Data;

@Data
public class QueryParams {

    private String keyword;
    private Integer pageNo;
    private Integer pageSize;

}
