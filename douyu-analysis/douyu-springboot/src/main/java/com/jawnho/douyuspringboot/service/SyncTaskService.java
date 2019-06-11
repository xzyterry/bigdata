package com.jawnho.douyuspringboot.service;

import com.jawnho.douyuspringboot.entity.model.QueryParams;
import com.jawnho.douyuspringboot.entity.model.SyncTaskModel;
import com.jawnho.douyuspringboot.response.DaoStatus;

public interface SyncTaskService {
    DaoStatus save(SyncTaskModel syncTaskModel);

    DaoStatus update(SyncTaskModel syncTaskModel);

    DaoStatus delete(SyncTaskModel syncTaskModel);

    DaoStatus findByPageAndKeyWord(QueryParams queryParams);
}
