package com.jawnho.douyuspringboot.entity.vo;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Builder
public class TbSqlVo {

    private int id;

    private String tbId;

    private String tbSql;

    private String tbName;

    private Integer isDelete;

    private String createTime;

    private String modifiedTime;
}
