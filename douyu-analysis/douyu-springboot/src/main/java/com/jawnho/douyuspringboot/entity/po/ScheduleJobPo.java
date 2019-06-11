package com.jawnho.douyuspringboot.entity.po;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "tbl_schedule_job")
@Entity
public class ScheduleJobPo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "cron")
    private String cron;

    @Column(name = "stId")
    private Long stId;

    // 0 - 代表正在执行  1 - 已删除  2 - 暂停
    @Column(name = "status")
    private Integer status;

    @Column(name = "create_time")
    private Long createTime;

    @Column(name = "modified_time")
    private Long modifiedTime;
}
