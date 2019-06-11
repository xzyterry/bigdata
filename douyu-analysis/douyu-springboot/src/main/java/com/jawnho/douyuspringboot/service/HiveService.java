package com.jawnho.douyuspringboot.service;

import com.jawnho.douyuspringboot.entity.model.SaveSql;
import com.jawnho.douyuspringboot.entity.vo.TbDetails;
import com.jawnho.douyuspringboot.response.DaoStatus;

import java.util.List;
import java.util.Map;

public interface HiveService {

    DaoStatus save(SaveSql saveSql);

    TbDetails findByTbId(String tb_id);

    List<Map<String, Object>> findAllTbList();

    List<Map<String, Object>> findFieldsByTbId(String tb_id);

    List<Map<String, Object>> reRun(String tb_id);
}
