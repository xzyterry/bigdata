package com.jawnho.douyuspringboot.entity.po;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_ds_info")
public class DsInfoPo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nickname",columnDefinition = "varchar(1000)")
    private String nickname;

    @Column(name = "driver",columnDefinition = "varchar(1000)")
    private String driver;

    @Column(name = "url",columnDefinition = "varchar(1000)")
    private String url;

    @Column(name = "username",columnDefinition = "varchar(1000)")
    private String username;

    @Column(name = "password",columnDefinition = "varchar(1000)")
    private String password;

    @Column(name = "createTime",columnDefinition = "varchar(1000)")
    private String createTime;

    @Column(name = "modifiedTime",columnDefinition = "varchar(1000)")
    private String modifiedTime;

    @Column(name = "isDelete")
    private int isDelete;


}
