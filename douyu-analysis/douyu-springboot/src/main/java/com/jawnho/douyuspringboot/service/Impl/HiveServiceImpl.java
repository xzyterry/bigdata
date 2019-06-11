package com.jawnho.douyuspringboot.service.Impl;

import com.jawnho.douyuspringboot.dao.TbSqlDaoRepository;
import com.jawnho.douyuspringboot.entity.model.SaveSql;
import com.jawnho.douyuspringboot.entity.model.TbField;
import com.jawnho.douyuspringboot.entity.po.TbSqlPo;
import com.jawnho.douyuspringboot.entity.vo.TbDetails;
import com.jawnho.douyuspringboot.hive.HiveJDBC;
import com.jawnho.douyuspringboot.response.DaoStatus;
import com.jawnho.douyuspringboot.response.JDBCStatus;
import com.jawnho.douyuspringboot.service.HiveService;
import com.jawnho.douyuspringboot.util.BeanMapUtil;
import com.jawnho.douyuspringboot.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class HiveServiceImpl implements HiveService {

    @Autowired
    private TbSqlDaoRepository tbSqlDaoRepository;

    @Override
    public DaoStatus save(SaveSql saveSql) {
        TbSqlPo isExisted = tbSqlDaoRepository.findByTbName(saveSql.getTb_name());
        DaoStatus daoStatus = null;
        if (isExisted == null) {
            TbSqlPo tbSqlPo = new TbSqlPo();
            tbSqlPo.setTbId(saveSql.getTb_id());
            tbSqlPo.setTbName(saveSql.getTb_name());
            tbSqlPo.setTbSql(saveSql.getTb_sql());
            tbSqlPo.setCreateTime(DateUtil.getTime());
            tbSqlPo.setModifiedTime(DateUtil.getTime());
            tbSqlPo.setIsDelete(0);
            tbSqlDaoRepository.save(tbSqlPo);
        } else {
            daoStatus = new DaoStatus("tb name is existed", null);
        }
        return daoStatus;
    }

    /*
       返回TbDetails
     */
    @Override
    public TbDetails findByTbId(String tb_id) {
        TbSqlPo tbSqlPo = tbSqlDaoRepository.findByTbId(tb_id);
        List<Map<String, Object>> tbFields = HiveJDBC.query("desc " + tb_id).getResult();
        String total_cnt = (String) HiveJDBC.query("select count(*) as cnt from " + tb_id)
                .getResult().get(0).get("cnt");
        List<Map<String, Object>> data = HiveJDBC.query("select * from " + tb_id + " limit 1000").getResult();

        List<Map<String, Object>> data2 = new ArrayList<>();
        data.forEach(f->{
            Map<String,Object> map = new HashMap<>();
            f.keySet().forEach(k->{
                map.put(k.substring(k.indexOf('.')+1),f.get(k));
            });
            data2.add(map);
        });

        TbDetails tbDetails = TbDetails.builder()
                .tb_id(tbSqlPo.getTbId())
                .tb_name(tbSqlPo.getTbName())
                .tb_create_time(tbSqlPo.getCreateTime())
                .tb_modified_time(tbSqlPo.getModifiedTime())
                .tb_fields(tbFields)
                .total_cnt(total_cnt)
                .data(data2)
                .build();
        return tbDetails;
    }

    @Override
    public List<Map<String, Object>> findAllTbList() {

        List<Map<String,Object>> result = new ArrayList<>();
         tbSqlDaoRepository.findAll().forEach(tbSqlPo -> {
             try {
                 result.add(BeanMapUtil.objectToMap(tbSqlPo));
             } catch (IllegalAccessException e) {
                 e.printStackTrace();
             }
         });
        return result;
    }

    @Override
    public List<Map<String, Object>> findFieldsByTbId(String tb_id) {

        return HiveJDBC.query("desc " + tb_id).getResult();
    }

    @Override
    public List<Map<String, Object>> reRun(String tb_id) {
        return null;
    }


}
