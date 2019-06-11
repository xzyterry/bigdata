package com.jawnho.douyuspringboot.entity.po;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_sync_task")
public class SyncTaskPo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "task_name",columnDefinition = "varchar(1000)")
    private String taskName;

    @Column(name = "ds_id")
    private Long dsId;

    @Column(name = "tb_name",columnDefinition = "varchar(1000)")
    private String tbName;

    @Column(name = "channel")
    private Integer channel;

    @Column(name = "cron",columnDefinition = "varchar(1000)")
    private String cron;

    @Column(name = "createTime",columnDefinition = "varchar(1000)")
    private String createTime;

    @Column(name = "modifiedTime",columnDefinition = "varchar(1000)")
    private String modifiedTime;

    @Column(name = "isDelete")
    private int isDelete;

}
