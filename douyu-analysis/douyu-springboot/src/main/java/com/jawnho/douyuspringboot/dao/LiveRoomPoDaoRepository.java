package com.jawnho.douyuspringboot.dao;

import com.jawnho.douyuspringboot.entity.po.LiveRoomPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LiveRoomPoDaoRepository extends JpaRepository<LiveRoomPo, Long>, JpaSpecificationExecutor<LiveRoomPo> {
    LiveRoomPo findByRoomid(Long roomid);

}

