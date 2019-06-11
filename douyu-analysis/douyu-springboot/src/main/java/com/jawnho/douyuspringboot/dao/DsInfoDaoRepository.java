package com.jawnho.douyuspringboot.dao;

import com.jawnho.douyuspringboot.entity.po.DsInfoPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DsInfoDaoRepository extends JpaRepository<DsInfoPo, Long>, JpaSpecificationExecutor<DsInfoPo> {

    DsInfoPo findByIdAndIsDelete(Long id,Integer isDelete);

}
