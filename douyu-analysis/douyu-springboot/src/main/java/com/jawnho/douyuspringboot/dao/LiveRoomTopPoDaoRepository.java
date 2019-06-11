package com.jawnho.douyuspringboot.dao;

import com.jawnho.douyuspringboot.entity.po.LiveRoomTopPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiveRoomTopPoDaoRepository extends JpaRepository<LiveRoomTopPo, Long>, JpaSpecificationExecutor<LiveRoomTopPo> {
    List<LiveRoomTopPo> findAllByOrderByOlDesc();

}

