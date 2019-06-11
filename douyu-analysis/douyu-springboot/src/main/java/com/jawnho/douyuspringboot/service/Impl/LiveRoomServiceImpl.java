package com.jawnho.douyuspringboot.service.Impl;

import com.jawnho.douyuspringboot.dao.LiveRoomPoDaoRepository;
import com.jawnho.douyuspringboot.dao.LiveRoomTopPoDaoRepository;
import com.jawnho.douyuspringboot.entity.po.LiveRoomPo;
import com.jawnho.douyuspringboot.entity.po.LiveRoomTopPo;
import com.jawnho.douyuspringboot.service.LiveRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LiveRoomServiceImpl implements LiveRoomService {

    @Autowired
    private LiveRoomPoDaoRepository liveRoomPoDaoRepository;

    @Autowired
    private LiveRoomTopPoDaoRepository liveRoomTopPoDaoRepository;

    @Override
    public LiveRoomPo findByRoomid(Long roomid) {
        return liveRoomPoDaoRepository.findByRoomid(roomid);
    }

    @Override
    public List<LiveRoomTopPo> findTop() {
        return liveRoomTopPoDaoRepository.findAllByOrderByOlDesc();
    }
}
