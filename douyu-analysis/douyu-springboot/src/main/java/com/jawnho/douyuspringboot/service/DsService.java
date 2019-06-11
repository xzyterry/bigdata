package com.jawnho.douyuspringboot.service;

import com.jawnho.douyuspringboot.entity.model.DsInfo;
import com.jawnho.douyuspringboot.entity.model.QueryParams;
import com.jawnho.douyuspringboot.response.DaoStatus;
import com.jawnho.douyuspringboot.response.JDBCStatus;

public interface DsService {
    JDBCStatus testConn(DsInfo dsInfo);

    DaoStatus saveConn(DsInfo dsInfo);

    DaoStatus update(DsInfo dsInfo);

    DaoStatus delete(DsInfo dsInfo);

    DaoStatus list(QueryParams queryParams);

    DaoStatus findByDsId(Long ds_id);
}
