package com.jawnho.douyuspringboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.jawnho.douyuspringboot.entity.model.SaveSql;
import com.jawnho.douyuspringboot.entity.model.TestSql;
import com.jawnho.douyuspringboot.entity.po.TbSqlPo;
import com.jawnho.douyuspringboot.entity.vo.TbDetails;
import com.jawnho.douyuspringboot.hive.HiveJDBC;
import com.jawnho.douyuspringboot.response.DaoStatus;
import com.jawnho.douyuspringboot.response.JDBCStatus;
import com.jawnho.douyuspringboot.response.ResultEntity;
import com.jawnho.douyuspringboot.service.HiveService;
import com.jawnho.douyuspringboot.util.DateUtil;
import com.jawnho.douyuspringboot.util.ResultUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/hive")
public class HiveController extends BaseController{

    @PostMapping("/testSql")
    @ResponseBody
    @ApiOperation(value = "/testSql", notes = "测试运行sql")
    public ResultEntity testSql(@RequestBody TestSql testSql) {
        JDBCStatus status = HiveJDBC.query(testSql.getSql());
        Map<String, Object> result = new HashMap<>();
        if (status.getException().equals("")) {
            result.put("data", status.getResult());
            String json = JSONObject.toJSONString(result);
            return new ResultEntity(0, "success", json);
        } else {
            result.put("data", status.getException());
            String json = JSONObject.toJSONString(result);
            return new ResultEntity(500, "failed", json);
        }

    }

    /**
     * 保存sql 并且结果存为一张 tb
     *
     * @param saveSql
     * @return
     */
    @PostMapping("/saveSql")
    @ResponseBody
    @ApiOperation(value = "/saveSql", notes = "保存sql")
    public ResultEntity saveSql(@RequestBody SaveSql saveSql) {


        String tb_id = UUID.randomUUID().toString().replace("-","_");
        saveSql.setTb_id(tb_id);
        String tb_sql = "create table if not exists " + tb_id + " as " + saveSql.getTb_sql();
        JDBCStatus status = HiveJDBC.createTable(tb_sql);
        DaoStatus daoStatus = null;
        if (status.getException().equals("")) {
            daoStatus = hiveService.save(saveSql);
        } else {
            daoStatus = new DaoStatus(status.getException(), null);
        }

        if (daoStatus == null) {
            return new ResultEntity(0, "success", "保存成功");
        } else {
            return new ResultEntity(500, "failed", daoStatus.getException());
        }
    }


    /**
     * 查询tb
     * 及其数据预览
     * @param tb_id
     * @return
     */
    @PostMapping("/queryTb/{tb_id}")
    @ResponseBody
    @ApiOperation(value = "/queryTb/{tb_id}", notes = "查看合表")
    public ResultEntity queryTb(@PathVariable String tb_id) {

        TbDetails tbDetails = hiveService.findByTbId(tb_id);

        return new ResultEntity(0, "success", ResultUtils.toJson(tbDetails));
    }

    /**
     * 查询tb list
     * @return
     */
    @PostMapping("/queryTbList")
    @ResponseBody
    @ApiOperation(value = "/queryTbList", notes = "查看合表")
    public ResultEntity queryTbList() {

        List<Map<String, Object>> result = hiveService.findAllTbList();

        return new ResultEntity(0, "success", ResultUtils.toJson(result));
    }


    /**
     * 查询tb fields
     *
     * @param tb_id
     * @return
     */
    @PostMapping("/queryTbFields/{tb_id}")
    @ResponseBody
    @ApiOperation(value = "/queryTbFields/{tb_id}", notes = "查看合表所有字段")
    public ResultEntity queryTbFields(@PathVariable String tb_id) {

        List<Map<String, Object>> result = hiveService.findFieldsByTbId(tb_id);

        return new ResultEntity(0, "success", ResultUtils.toJson(result));
    }

    /**
     * 触发单表合表
     *
     * @param tb_id
     * @return
     */
    @PostMapping("/reRun/{tb_id}")
    @ResponseBody
    @ApiOperation(value = "/reRun/{tb_id}", notes = "查看合表")
    public ResultEntity reRun(@PathVariable String tb_id) {

        List<Map<String, Object>> result = hiveService.reRun(tb_id);

        return new ResultEntity(0, "success", ResultUtils.toJson(result));
    }

    /**
     * 触发级联合表
     *
     * @param testSql
     * @return
     */
    @PostMapping("/reCascadeRun")
    @ResponseBody
    @ApiOperation(value = "/reCascadeRun", notes = "查看合表")
    public ResultEntity reCascadeRun(@RequestBody TestSql testSql) {

        return new ResultEntity(0, "", "");
    }


}
