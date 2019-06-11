package com.jawnho.douyuspringboot;

import com.alibaba.fastjson.JSONObject;
import com.jawnho.douyuspringboot.dao.LiveRoomPoDaoRepository;
import com.jawnho.douyuspringboot.entity.po.LiveRoomPo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DouyuSpringbootApplicationTests {

    @Autowired
    private LiveRoomPoDaoRepository repository;

    @Test
    public void contextLoads() {

        List<LiveRoomPo> all = repository.findAll();
        all.forEach(a->{
            System.out.println(JSONObject.toJSONString(a));
        });

    }

}
