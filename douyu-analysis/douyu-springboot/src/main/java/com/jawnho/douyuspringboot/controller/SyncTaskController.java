package com.jawnho.douyuspringboot.controller;

import com.jawnho.douyuspringboot.Conn.RdsmsUtil;
import com.jawnho.douyuspringboot.entity.model.QueryParams;
import com.jawnho.douyuspringboot.entity.model.SyncTaskModel;
import com.jawnho.douyuspringboot.entity.po.DsInfoPo;
import com.jawnho.douyuspringboot.response.DaoStatus;
import com.jawnho.douyuspringboot.response.JDBCStatus;
import com.jawnho.douyuspringboot.response.ResultEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 2.任务列表(taskList)add edit delete list(page+keyword)
 */

@Controller
@RequestMapping("/synctask")
public class SyncTaskController extends BaseController {

    //add
    @PostMapping("/add")
    @ResponseBody
    @ApiOperation(value = "/add", notes = "保存同步任务")
    public ResultEntity add(@RequestBody SyncTaskModel syncTaskModel) {

        JDBCStatus jdbcStatus = check(syncTaskModel);
        String exception = jdbcStatus.getException();

        DaoStatus daoStatus = new DaoStatus();
        if (exception.equals(""))
            daoStatus = syncTaskService.save(syncTaskModel);

        exception += daoStatus.getException();

        if (exception.equals("")) {
            return new ResultEntity(0, "success", "已保存");
        } else {
            return new ResultEntity(500, "failed", exception);
        }
    }

    //edit

    /**
     * TODO 修改之前先check一下
     */
    @PostMapping("/edit")
    @ResponseBody
    @ApiOperation(value = "/edit", notes = "修改同步任务")
    public ResultEntity edit(@RequestBody SyncTaskModel syncTaskModel) {

        //查询是否存在该ds
        JDBCStatus jdbcStatus = check(syncTaskModel);
        String exception = jdbcStatus.getException();

        DaoStatus daoStatus = new DaoStatus();
        if (exception.equals(""))
            daoStatus = syncTaskService.update(syncTaskModel);

        exception += daoStatus.getException();

        if (exception.equals("")) {
            return new ResultEntity(0, "success", "已保存");
        } else {
            return new ResultEntity(500, "failed", exception);
        }

    }

    //delete
    /*
        TODO
        1.从instance中先获取任务状态,如果任务正在运行或者待调度 则先返回提示警告码
          如果返回同意删除 则先删除所有instance 再删除task
        2.如果未被占用 及空闲未被调度stop状态 则直接删除所有instance 和 task
     */
    @PostMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "/delete", notes = "删除同步任务")
    public ResultEntity delete(@RequestBody SyncTaskModel syncTaskModel) {

        //查询是否存在该ds
        JDBCStatus jdbcStatus = check(syncTaskModel);
        String exception = jdbcStatus.getException();

        DaoStatus daoStatus = new DaoStatus();
        if (exception.equals(""))
            daoStatus = syncTaskService.delete(syncTaskModel);

        exception += daoStatus.getException();

        if (exception.equals("")) {
            return new ResultEntity(0, "已保存");
        } else {
            return new ResultEntity(500, exception);
        }

    }


    //list
    @PostMapping("/list")
    @ResponseBody
    @ApiOperation(value = "/list", notes = "分页查询task")
    public ResultEntity list(QueryParams queryParams) {

        DaoStatus daoStatus = syncTaskService.findByPageAndKeyWord(queryParams);
        if (daoStatus.getException().equals(""))
            return new ResultEntity(0, daoStatus.getObject());
        else
            return new ResultEntity(500, daoStatus.getException());
    }

    //fields



    //检验同步任务  1.ds_id是否存在该ds 2.ds是否连通
    private JDBCStatus check(SyncTaskModel syncTaskModel) {

        DaoStatus daoStatus = dsService.findByDsId(syncTaskModel.getDs_id());
        JDBCStatus jdbcStatus = new JDBCStatus();

        //如果存在 则查看是否连通 否则返回报错信息
        if (daoStatus.getException().equals("")) {
            DsInfoPo po = (DsInfoPo) daoStatus.getObject();

            jdbcStatus = RdsmsUtil.getConn(po.getUrl(), po.getDriver(), po.getUsername(), po.getPassword());

        }
        String exception = daoStatus.getException();
        exception = exception.equals("") ? "" : "\n";
        exception += jdbcStatus.getException();

        return new JDBCStatus(exception, (DsInfoPo) daoStatus.getObject(), null);
    }

    /**
     * todo 检查是否被占用 即检查 task_id对应的intance是否正在被运行
     *
     * @param task_id
     * @return
     */
    private JDBCStatus checkOccupy(Long task_id) {

        return null;
    }


}
