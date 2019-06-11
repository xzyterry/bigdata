package com.jawnho.douyuspringboot.service;

import com.jawnho.douyuspringboot.entity.po.LiveRoomPo;
import com.jawnho.douyuspringboot.entity.po.LiveRoomTopPo;

import java.util.List;

public interface LiveRoomService {

    public LiveRoomPo findByRoomid(Long roomid);
    public List<LiveRoomTopPo> findTop();
}
