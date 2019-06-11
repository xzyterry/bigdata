package com.jawnho.douyuspringboot.service.Impl;

import com.jawnho.douyuspringboot.common.Constant;
import com.jawnho.douyuspringboot.dao.SyncInstanceDaoRepository;
import com.jawnho.douyuspringboot.dao.SyncTaskDaoRepository;
import com.jawnho.douyuspringboot.entity.model.QueryParams;
import com.jawnho.douyuspringboot.entity.model.SyncInstanceModel;
import com.jawnho.douyuspringboot.entity.model.SyncTaskModel;
import com.jawnho.douyuspringboot.entity.po.SyncInstancePo;
import com.jawnho.douyuspringboot.entity.po.SyncTaskPo;
import com.jawnho.douyuspringboot.entity.vo.Pager;
import com.jawnho.douyuspringboot.entity.vo.SyncInstanceVo;
import com.jawnho.douyuspringboot.response.DaoStatus;
import com.jawnho.douyuspringboot.schedule.ScheduleQuartzJob;
import com.jawnho.douyuspringboot.service.SyncInstanceService;
import com.jawnho.douyuspringboot.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SyncInstanceServiceImpl implements SyncInstanceService {
    // 获取工厂类
    private StdSchedulerFactory sf = new StdSchedulerFactory();


    @Autowired
    private SyncTaskDaoRepository syncTaskDaoRepository;

    @Autowired
    private SyncInstanceDaoRepository syncInstanceDaoRepository;

    @PostConstruct
    public void init() {
        List<SyncInstancePo> poList = syncInstanceDaoRepository.findAllByStatus(1);
        poList.forEach(po -> {
            startScheduleByInit(po);
        });
    }

    /**
     * 初始化时开启定时任务
     */
    private void startScheduleByInit(SyncInstancePo po) {
        SyncTaskPo syncTaskPo = syncTaskDaoRepository.findByIdAndIsDelete(po.getStId(), 0);
        try {
            Scheduler scheduler = sf.getScheduler();
            startJob(scheduler, po.getGroupName(), po.getJobName(), syncTaskPo);
            scheduler.start();
        } catch (Exception e) {
            log.error("exception:{}", e);
        }
    }

    /**
     * 开启定时任务
     */
    @Override
    public DaoStatus startSchedule(SyncTaskModel model) {

        Long st_id = model.getSt_id();

        String message = "";
        //保存到instance中
        try {
            SyncTaskPo syncTaskPo = syncTaskDaoRepository.findByIdAndIsDelete(model.getSt_id(), 0);
            String groupName = syncTaskPo.getTaskName();
            String jobName = groupName + DateUtil.getTime().replace(" ", "_");

            Scheduler scheduler = sf.getScheduler();
            startJob(scheduler, groupName, jobName, syncTaskPo);
            scheduler.start();
            SyncInstancePo po = new SyncInstancePo();
            po.setStId(st_id);
            po.setGroupName(groupName);
            po.setJobName(jobName);
            po.setStatus(1);
            po.setIsDelete(0);
            po.setCreateTime(DateUtil.getTime());
            po.setModifiedTime(DateUtil.getTime());
            po.setConfPath("");
            po.setLogPath("");
            syncInstanceDaoRepository.save(po);
        } catch (Exception e) {
            log.error("exception:{}", e);
            message = e.getMessage();
        }

        return new DaoStatus(message, null);
    }

    @Override
    public DaoStatus scheduleUpdateCorn(SyncInstanceModel model) {

        String message = "";
        try {

            SyncInstancePo po = syncInstanceDaoRepository.findByIdAndIsDelete(model.getSi_id(), 0);
            SyncTaskPo syncTaskPo = syncTaskDaoRepository.findByIdAndIsDelete(po.getStId(), 0);

            Scheduler scheduler = sf.getScheduler();
            TriggerKey triggerKey = new TriggerKey(po.getJobName(), po.getGroupName());
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            String oldTime = cronTrigger.getCronExpression();

            if (!oldTime.equalsIgnoreCase(model.getCron())) {
                CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(model.getCron());
                CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(po.getJobName(), po.getGroupName())
                        .withSchedule(cronScheduleBuilder).build();
                scheduler.rescheduleJob(triggerKey, trigger);

                syncTaskPo.setCron(model.getCron());
                syncTaskDaoRepository.save(syncTaskPo);

            }
        } catch (Exception e) {
            log.info("exception:{}", e);
            message = e.getMessage();
        }
        return new DaoStatus(message, null);
    }

    /**
     * 任务 - 暂停
     * todo kill 同步进程
     */
    @Override
    public DaoStatus schedulePause(SyncInstanceModel model) {

        SyncInstancePo po = syncInstanceDaoRepository.findByIdAndStatus(model.getSi_id(), 1);
        String message = "";
        try {
            Scheduler scheduler = sf.getScheduler();
            JobKey jobKey = new JobKey(po.getJobName(), po.getGroupName());
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);

            if (jobDetail == null)
                message = "no such jobDetail";

            scheduler.pauseJob(jobKey);
            po.setStatus(0);
            syncInstanceDaoRepository.save(po);
        } catch (Exception e) {
            log.error("exception:{}", e);
            message += e.getMessage();
        }
        return new DaoStatus(message, null);
    }

    /**
     * 任务 - 恢复
     */
    @Override
    public DaoStatus scheduleResume(SyncInstanceModel model) {


        String message = "";
        try {
            SyncInstancePo po = syncInstanceDaoRepository
                    .findByIdAndStatus(model.getSi_id(), 0);

            Scheduler scheduler = sf.getScheduler();
            JobKey jobKey = new JobKey(po.getJobName(), po.getGroupName());
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail == null)
                message = "no such jobDetail";
            scheduler.resumeJob(jobKey);
            po.setStatus(1);
            syncInstanceDaoRepository.save(po);
        } catch (Exception e) {
            log.error("exception:{}", e);
            message += e.getMessage();
        }
        return new DaoStatus(message, null);
    }

    /**
     * 任务 - 删除调度
     * todo kill 同步任务进程
     */
    @Override
    public DaoStatus scheduleDelete(SyncInstanceModel model) {

        SyncInstancePo po = syncInstanceDaoRepository.findByIdAndIsDelete(model.getSi_id(), 0);
        String message = "";
        try {
            Scheduler scheduler = sf.getScheduler();
            JobKey jobKey = new JobKey(po.getJobName(), po.getGroupName());
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail == null)
                message = "no such jobDetail";
            scheduler.deleteJob(jobKey);
            po.setIsDelete(1);
            syncInstanceDaoRepository.save(po);
        } catch (Exception e) {
            log.error("exception:{}", e);
            message += e.getMessage();
        }
        return new DaoStatus(message, null);
    }


    @Override
    public DaoStatus getAllScheduleJob(QueryParams queryParams) {

        PageRequest pageRequest = PageRequest.of(queryParams.getPageNo() - 1, queryParams.getPageSize(),
                new Sort(Sort.Direction.ASC, "createTime"));


        Specification<SyncInstancePo> specification = new Specification<SyncInstancePo>() {
            @Override
            public Predicate toPredicate(Root<SyncInstancePo> root,
                                         CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();

                Predicate isDeleted = criteriaBuilder.equal(root.get("isDeleted"), 0);
                Predicate containKeyword = criteriaBuilder.like(root.get("group_name"), "%" + queryParams.getKeyword() + "%");
                list.add(isDeleted);
                list.add(containKeyword);

                Predicate[] predicates = list.toArray(new Predicate[0]);
                return criteriaBuilder.and(predicates);
            }
        };

        Page<SyncInstancePo> page = syncInstanceDaoRepository.findAll(specification, pageRequest);
        List<SyncInstanceVo> voList = new ArrayList<>();
        page.getContent().forEach(po -> {
            SyncInstanceVo vo = SyncInstanceVo.builder()
                    .id(po.getId())
                    .groupName(po.getGroupName())
                    .jobName(po.getJobName())
                    .confPath(po.getConfPath())
                    .logPath(po.getLogPath())
                    .stId(po.getStId())
                    .status(po.getStatus())
                    .createTime(po.getCreateTime())
                    .modifiedTime(po.getModifiedTime())
                    .isDelete(po.getIsDelete())
                    .build();
            voList.add(vo);
        });

        Pager<SyncInstanceVo> pager = new Pager<>();
        pager.setPageNo(queryParams.getPageNo());
        pager.setPageSize(queryParams.getPageSize());
        pager.setTotalPages(page.getTotalPages());
        pager.setTotalSize(page.getTotalElements());
        pager.setDatas(voList);

        return new DaoStatus("", pager);

    }

    private void startJob(Scheduler scheduler, String groupName, String jobName, SyncTaskPo syncTaskPo) throws SchedulerException {

        // 通过JobBuilder构建JobDetail实例，JobDetail规定只能是实现Job接口的实例
        // 将参数写入任务中
        JobDataMap map = new JobDataMap();
        map.put(Constant.jobKey, syncTaskPo);
        // JobDetail 是具体Job实例
        JobDetail jobDetail = JobBuilder.newJob(ScheduleQuartzJob.class).withIdentity(jobName, groupName)
                .usingJobData(map)
                .build();

        // 基于表达式构建触发器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(syncTaskPo.getCron());
        // CronTrigger表达式触发器 继承于Trigger
        // TriggerBuilder 用于构建触发器实例
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobName, groupName)
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }
}
