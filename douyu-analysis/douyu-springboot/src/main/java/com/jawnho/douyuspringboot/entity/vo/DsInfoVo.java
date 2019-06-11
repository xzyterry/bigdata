package com.jawnho.douyuspringboot.entity.vo;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
public class DsInfoVo {

    private Long id;

    private String nickname;

    private String driver;

    private String url;

    private String username;

    private String password;

    private String createTime;

    private String modifiedTime;

    private int isDelete;


}
