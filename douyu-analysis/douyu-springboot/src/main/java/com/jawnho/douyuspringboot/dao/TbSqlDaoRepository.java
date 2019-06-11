package com.jawnho.douyuspringboot.dao;

import com.jawnho.douyuspringboot.entity.po.TbSqlPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TbSqlDaoRepository extends JpaRepository<TbSqlPo, Long>, JpaSpecificationExecutor<TbSqlPo> {

    public TbSqlPo findByTbName(String tbName);
    public TbSqlPo findByTbId(String tb_id);
}
