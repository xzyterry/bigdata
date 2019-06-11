package com.jawnho.douyuspringboot.dao;


import com.jawnho.douyuspringboot.entity.po.SyncTaskPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SyncTaskDaoRepository extends JpaRepository<SyncTaskPo,Long>, JpaSpecificationExecutor<SyncTaskPo> {
    SyncTaskPo findByIdAndIsDelete(Long id, int i);
}
