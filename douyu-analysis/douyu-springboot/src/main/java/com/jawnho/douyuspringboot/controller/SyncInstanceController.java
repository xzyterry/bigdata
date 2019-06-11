package com.jawnho.douyuspringboot.controller;


import com.jawnho.douyuspringboot.entity.model.ScheduleJobModel;
import com.jawnho.douyuspringboot.entity.model.SyncInstanceModel;
import com.jawnho.douyuspringboot.entity.model.SyncTaskModel;
import com.jawnho.douyuspringboot.response.ResultEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;



//运行实例(instance) list(page+keyword) start stop resume delete logs

@RestController
@RequestMapping("/sync/instance")
public class SyncInstanceController extends BaseController{

    /**
     * 获取到task_id 查询 task
     * 根据task new 实例 保存
     * @param syncTaskModel
     * @return
     */
    @PostMapping("/start")
    @ApiOperation(value = "start",notes = "运行实例")
    public ResultEntity start(@RequestBody SyncTaskModel syncTaskModel){
        syncInstanceService.startSchedule( syncTaskModel);

        return null;
    }

    @PostMapping("/stop")
    @ApiOperation(value = "stop",notes = "暂停")
    public ResultEntity stop(@RequestBody SyncInstanceModel syncInstanceModel){
        syncInstanceService.schedulePause( syncInstanceModel);
        return null;
    }


    @PostMapping("/resume")
    @ApiOperation(value = "resume",notes = "运行实例")
    public ResultEntity resume(@RequestBody SyncInstanceModel model){
        syncInstanceService.scheduleResume( model);
        return null;
    }

    @PostMapping("/delete")
    @ApiOperation(value = "delete",notes = "运行实例")
    public ResultEntity delete(@RequestBody SyncInstanceModel model){
        syncInstanceService.scheduleDelete( model);
        return null;
    }

//    @PostMapping("/logs")
//    @ApiOperation(value = "logs",notes = "运行实例")
//    public ResultEntity logs(@RequestBody SyncInstanceModel model){
//        syncInstanceService.s( model);
//        return null;
//    }

}
