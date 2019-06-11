package com.jawnho.douyuspringboot.entity.model;

import lombok.Data;

@Data
public class SyncTaskModel {

    private Long st_id;
    private String st_name;
    private Long ds_id;
    private String tb_name;
    private int channel;
    private String cron;

}
