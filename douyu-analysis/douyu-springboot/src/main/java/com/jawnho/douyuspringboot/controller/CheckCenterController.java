package com.jawnho.douyuspringboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.jawnho.douyuspringboot.config.WebSocketServer;
import com.jawnho.douyuspringboot.entity.po.LiveRoomPo;
import com.jawnho.douyuspringboot.entity.po.LiveRoomTopPo;
import com.jawnho.douyuspringboot.response.ResultEntity;
import com.jawnho.douyuspringboot.service.LiveRoomService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/douyu")
public class CheckCenterController {


    @Autowired
    private LiveRoomService liveRoomService;

    //页面请求
    @GetMapping("/socket/{cid}")
    @ApiOperation(value = "/sockect/{cid}", notes = "页面请求")
    public ModelAndView socket(@PathVariable String cid) {
        ModelAndView mav = new ModelAndView("/socket");
        mav.addObject("cid", cid);
        return mav;
    }

    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    @ApiOperation(value = "/sockect/{cid}", notes = "推送数据接口")
    public ResultEntity pushToWeb(@PathVariable String cid, String message) {

        try {
            WebSocketServer.sendInfo(message, cid);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultEntity(cid + "#" + e.getMessage());
        }
        return new ResultEntity(cid);
    }

    //推送数据接口
    @ResponseBody
    @RequestMapping(value = "/socket/push2/{cid}", method = RequestMethod.GET)
    @ApiOperation(value = "/sockect/push2/{cid}", notes = "推送数据接口")
    public void sendOnline(@PathVariable String cid, String message) throws InterruptedException {

        while (true) {
            LiveRoomPo liveRoomPo = liveRoomService.findByRoomid(Long.parseLong(cid));
            Map<String, Object> msg = new HashMap<>();
            msg.put("data", liveRoomPo);
            String json = JSONObject.toJSONString(msg);
            WebSocketServer.sendMsg(json, cid);
            Thread.sleep(5000);
        }
    }

    @GetMapping("/test")
    public String test() {
        Long roomid = 606118L;
        liveRoomService.findByRoomid(roomid);
        return "";
    }


    //推送数据接口
    @ResponseBody
    @RequestMapping(value = "/top", method = RequestMethod.GET)
    @ApiOperation(value = "/top", notes = "返回斗鱼在线人数top")
    public ResultEntity getTop() throws InterruptedException {

        List<LiveRoomTopPo> liveRoomTopPoList = liveRoomService.findTop();
        Map<String, Object> msg = new HashMap<>();
        msg.put("data", liveRoomTopPoList);
        String json = JSONObject.toJSONString(msg);
        return new ResultEntity(0, "success", json);
    }

}
