package com.jawnho.douyuspringboot.controller;

import com.jawnho.douyuspringboot.service.DsService;
import com.jawnho.douyuspringboot.service.HiveService;
import com.jawnho.douyuspringboot.service.SyncInstanceService;
import com.jawnho.douyuspringboot.service.SyncTaskService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {

    @Autowired
    protected DsService dsService;

    @Autowired
    protected HiveService hiveService;

    @Autowired
    protected SyncTaskService syncTaskService;

    @Autowired
    protected SyncInstanceService syncInstanceService;

}
