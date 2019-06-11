package com.jawnho.douyuspringboot.service.Impl;

import com.jawnho.douyuspringboot.Conn.RdsmsUtil;
import com.jawnho.douyuspringboot.dao.DsInfoDaoRepository;
import com.jawnho.douyuspringboot.entity.model.DsInfo;
import com.jawnho.douyuspringboot.entity.model.QueryParams;
import com.jawnho.douyuspringboot.entity.po.DsInfoPo;
import com.jawnho.douyuspringboot.entity.vo.DsInfoVo;
import com.jawnho.douyuspringboot.entity.vo.Pager;
import com.jawnho.douyuspringboot.response.DaoStatus;
import com.jawnho.douyuspringboot.response.JDBCStatus;
import com.jawnho.douyuspringboot.service.DsService;
import com.jawnho.douyuspringboot.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DsServiceImpl implements DsService {

    @Autowired
    private DsInfoDaoRepository dsInfoDaoRepository;

    @Override
    public JDBCStatus testConn(DsInfo dsInfo) {
        return RdsmsUtil.getConn(dsInfo);
    }

    @Override
    public DaoStatus saveConn(DsInfo dsInfo) {

        DsInfoPo po = toPo(dsInfo);
        po.setIsDelete(0);
        po.setCreateTime(DateUtil.getTime());
        po.setModifiedTime(DateUtil.getTime());
        po.setIsDelete(0);
        return save(po);
    }

    @Override
    public DaoStatus update(DsInfo dsInfo) {
        DsInfoPo po = toPo(dsInfo);
        po.setModifiedTime(DateUtil.getTime());
        return save(po);
    }

    @Override
    public DaoStatus delete(DsInfo dsInfo) {
        DsInfoPo po = toPo(dsInfo);
        po.setIsDelete(1);
        po.setModifiedTime(DateUtil.getTime());
        return save(po);

    }

    @Override
    public DaoStatus list(QueryParams queryParams) {
        PageRequest pageRequest = PageRequest.of(queryParams.getPageNo() - 1, queryParams.getPageSize(),
                new Sort(Sort.Direction.DESC, "createTime"));


        Specification<DsInfoPo> specification = new Specification<DsInfoPo>() {
            @Override
            public Predicate toPredicate(Root<DsInfoPo> root,
                                         CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();

                Predicate isDeleted = criteriaBuilder.equal(root.get("isDeleted"), 0);
                Predicate containKeyword = criteriaBuilder.like(root.get("nickname"),"%"+ queryParams.getKeyword()+"%");
                list.add(isDeleted);
                list.add(containKeyword);

                Predicate[] predicates = list.toArray(new Predicate[0]);
                return criteriaBuilder.and(predicates);
            }
        };

        Page<DsInfoPo> page = dsInfoDaoRepository.findAll(specification, pageRequest);
        List<DsInfoVo> voList = new ArrayList<>();
        page.getContent().forEach(po -> {
            DsInfoVo vo = DsInfoVo.builder()
                    .id(po.getId())
                    .username(po.getUsername())
                    .password(po.getPassword())
                    .driver(po.getDriver())
                    .url(po.getUrl())
                    .createTime(po.getCreateTime())
                    .modifiedTime(po.getModifiedTime())
                    .isDelete(po.getIsDelete())
                    .build();
            voList.add(vo);
        });
        Pager<DsInfoVo> pager = new Pager<>();
        pager.setPageNo(queryParams.getPageNo());
        pager.setPageSize(queryParams.getPageSize());
        pager.setTotalPages(page.getTotalPages());
        pager.setTotalSize(page.getTotalElements());
        pager.setDatas(voList);


        return new DaoStatus("",pager);

    }

    @Override
    public DaoStatus findByDsId(Long ds_id) {

        DsInfoPo po = null;
        String exception = "";
        try {
            po = dsInfoDaoRepository.findByIdAndIsDelete(ds_id, 0);
        } catch (Exception e) {
            e.getStackTrace();
            exception = e.getMessage();
        }
        return new DaoStatus(exception, po);

    }

    private DaoStatus save(DsInfoPo po){
        DsInfoPo save = null;
        String exception = "";
        try {
            save = dsInfoDaoRepository.save(po);
        } catch (Exception e) {
            e.getStackTrace();
            exception = e.getMessage();
        }
        return new DaoStatus(exception, save);
    }

    private DsInfoPo toPo(DsInfo dsInfo) {
        DsInfoPo po = new DsInfoPo();
        po.setId(dsInfo.getId());
        po.setNickname(dsInfo.getNickname());
        po.setDriver(dsInfo.getDriver());
        po.setUrl(dsInfo.getUrl());
        po.setUsername(dsInfo.getUsername());
        po.setPassword(dsInfo.getPassword());
        return po;
    }
}
