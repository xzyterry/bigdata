package com.jawnho.douyuspringboot.service;


import com.jawnho.douyuspringboot.entity.model.QueryParams;
import com.jawnho.douyuspringboot.entity.model.SyncInstanceModel;
import com.jawnho.douyuspringboot.entity.model.SyncTaskModel;
import com.jawnho.douyuspringboot.response.DaoStatus;

import java.util.List;

public interface SyncInstanceService {

    /**
     * 任务 - 开启
     */
    public DaoStatus startSchedule(SyncTaskModel model);

    /**
     * 获取所有任务
     */
    public DaoStatus getAllScheduleJob(QueryParams queryParams);

    /**
     * 任务 - 更新corn
     */
    public DaoStatus scheduleUpdateCorn(SyncInstanceModel model);

    /**
     * 任务 - 暂停
     */
    public DaoStatus schedulePause(SyncInstanceModel model);

    /**
     * 任务 - 恢复
     */
    public DaoStatus scheduleResume(SyncInstanceModel model);

    /**
     * 任务 - 删除调度
     */
    public DaoStatus scheduleDelete(SyncInstanceModel model);

}
