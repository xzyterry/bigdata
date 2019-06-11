package com.jawnho.douyuspringboot.entity.vo;


import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class SyncInstanceVo {


    private Long id;


    private String groupName;


    private String jobName;


    private Long stId;


    private String confPath;


    private String logPath;

    //1:运行中 2:调度中  0:暂停 -1:已删除

    private Integer status;


    private String createTime;


    private String modifiedTime;


    private int isDelete;

}
