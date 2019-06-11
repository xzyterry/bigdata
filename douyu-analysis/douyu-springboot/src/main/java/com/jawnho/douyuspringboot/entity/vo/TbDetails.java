package com.jawnho.douyuspringboot.entity.vo;

import com.jawnho.douyuspringboot.entity.model.TbField;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 合表详情
 * 1.tb_id tb_name tb_fields tb_create_time tb_modified_time total_cnt
 * 2.data limit 1000
 */
@Data
@Builder
public class TbDetails {

    private String tb_id;
    private String tb_name;
    private List<Map<String,Object>> tb_fields;
    private String tb_create_time;
    private String tb_modified_time;
    private String total_cnt;

    private List<Map<String,Object>> data;


}
