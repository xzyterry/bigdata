package com.jawnho.douyuspringboot.service.Impl;

import com.jawnho.douyuspringboot.entity.model.QueryParams;
import com.jawnho.douyuspringboot.entity.model.SyncTaskModel;
import com.jawnho.douyuspringboot.response.DaoStatus;
import com.jawnho.douyuspringboot.service.SyncTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SyncTaskServiceImpl implements SyncTaskService {

    @Override
    public DaoStatus save(SyncTaskModel syncTaskModel) {
        return null;
    }

    @Override
    public DaoStatus update(SyncTaskModel syncTaskModel) {
        return null;
    }

    @Override
    public DaoStatus delete(SyncTaskModel syncTaskModel) {
        return null;
    }

    @Override
    public DaoStatus findByPageAndKeyWord(QueryParams queryParams) {
        return null;
    }
}
