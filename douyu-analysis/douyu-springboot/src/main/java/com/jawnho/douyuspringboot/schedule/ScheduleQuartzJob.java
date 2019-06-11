package com.jawnho.douyuspringboot.schedule;

import com.alibaba.fastjson.JSONObject;
import com.jawnho.douyuspringboot.common.Constant;
import com.jawnho.douyuspringboot.entity.po.SyncInstancePo;
import com.jawnho.douyuspringboot.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * 同步任务实例
 * 1.调用quartz定时任务
 * 2.
 *  1>java调用ssh
 *  2>远程生成.json
 *  3>远程执行python语句
 *  4>并且获取指定路径下的
 */

@Slf4j
public class ScheduleQuartzJob implements Job {


    @Override
    public void execute(JobExecutionContext context) {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        SyncInstancePo jobDetailPo = JsonUtil.toBean(JsonUtil.toJson(jobDataMap.get(Constant.jobKey)), SyncInstancePo.class);

        //调度
    }



}
