package com.jawnho.douyuspringboot.controller;

import com.jawnho.douyuspringboot.entity.model.DsInfo;
import com.jawnho.douyuspringboot.entity.model.QueryParams;
import com.jawnho.douyuspringboot.response.DaoStatus;
import com.jawnho.douyuspringboot.response.JDBCStatus;
import com.jawnho.douyuspringboot.response.ResultEntity;
import com.jawnho.douyuspringboot.util.ResultUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 源数据库信息 testConn save edit delete list(分页+keyword)
 */

@Controller
@RequestMapping("/ds")
public class DsController extends BaseController{


    //testConn
    @PostMapping("/testConn")
    @ResponseBody
    @ApiOperation(value = "/testConn", notes = "测试连通性")
    public ResultEntity testConn(@RequestBody DsInfo dsInfo) {

        JDBCStatus jdbcStatus = dsService.testConn(dsInfo);
        if(jdbcStatus.getException().equals("")){
            return new ResultEntity(0,"success","连通");
        }else{
            return new ResultEntity(500,"failed",jdbcStatus.getException());
        }

    }

    //save
    @PostMapping("/saveConn")
    @ResponseBody
    @ApiOperation(value = "/saveConn", notes = "保存连接信息")
    public ResultEntity saveConn(@RequestBody DsInfo dsInfo) {

        DaoStatus daoStatus = dsService.saveConn(dsInfo);
        if(daoStatus.getException().equals("")){
            return new ResultEntity(0,"success","已保存");
        }else{
            return new ResultEntity(500,"failed",daoStatus.getException());
        }
    }

    //edit
    @PostMapping("/edit")
    @ResponseBody
    @ApiOperation(value = "/edit", notes = "编辑修改")
    public ResultEntity edit(@RequestBody DsInfo dsInfo) {
        DaoStatus daoStatus = dsService.update(dsInfo);
        if(daoStatus.getException().equals("")){
            return new ResultEntity(0,"success","已保存");
        }else{
            return new ResultEntity(500,"failed",daoStatus.getException());
        }
    }

    //delete
    @PostMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "/delete", notes = "删除数据源")
    public ResultEntity delete(@RequestBody DsInfo dsInfo) {
        DaoStatus daoStatus = dsService.delete(dsInfo);
        if(daoStatus.getException().equals("")){
            return new ResultEntity(0,"success","已保存");
        }else{
            return new ResultEntity(500,"failed",daoStatus.getException());
        }


    }
    //list
    @PostMapping("/list")
    @ResponseBody
    @ApiOperation(value = "/list", notes = "查询数据源列表")
    public ResultEntity list(@RequestBody QueryParams queryParams) {

        DaoStatus daoStatus = dsService.list(queryParams);
        if(daoStatus.getException().equals("")){
            return new ResultEntity(0,"success", ResultUtils.toJson(daoStatus.getObject()));
        }else{
            return new ResultEntity(500,"failed",daoStatus.getException());
        }
    }
}
