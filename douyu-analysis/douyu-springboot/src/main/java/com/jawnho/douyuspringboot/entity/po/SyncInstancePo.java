package com.jawnho.douyuspringboot.entity.po;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tb_sync_instance")
public class SyncInstancePo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "group_name",columnDefinition = "varchar(1000)")
    private String groupName;

    @Column(name = "job_name",columnDefinition = "varchar(1000)")
    private String jobName;

    @Column(name = "st_id")
    private Long stId;

    @Column(name = "conf_path",columnDefinition = "varchar(1000)")
    private String confPath;

    @Column(name = "log_path",columnDefinition = "varchar(1000)")
    private String logPath;

    //1:运行中 2:调度中  0:暂停 -1:已删除
    @Column(name = "status")
    private Integer status;

    @Column(name = "createTime",columnDefinition = "varchar(1000)")
    private String createTime;

    @Column(name = "modifiedTime",columnDefinition = "varchar(1000)")
    private String modifiedTime;

    @Column(name = "isDelete")
    private int isDelete;

}
