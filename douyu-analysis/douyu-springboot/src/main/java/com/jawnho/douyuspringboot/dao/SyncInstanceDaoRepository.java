package com.jawnho.douyuspringboot.dao;

import com.jawnho.douyuspringboot.entity.po.SyncInstancePo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyncInstanceDaoRepository extends JpaRepository<SyncInstancePo, Long>, JpaSpecificationExecutor<SyncInstancePo> {
    public SyncInstancePo findByIdAndIsDelete(Long id, Integer isDelete);

    public List<SyncInstancePo> findAllByStatus(Integer status);

    SyncInstancePo findByIdAndStatus(Long si_id, int i);
}
