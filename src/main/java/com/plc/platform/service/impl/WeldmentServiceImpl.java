package com.plc.platform.service.impl;

import com.plc.platform.common.Constants;
import com.plc.platform.dao.WeldmentDao;
import com.plc.platform.entity.Weldment;
import com.plc.platform.queryBo.BaseQueryBo;
import com.plc.platform.queryBo.WeldmentQueryBo;
import com.plc.platform.service.WeldmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service("weldmentService")
public class WeldmentServiceImpl implements WeldmentService {

    @Resource
    private WeldmentDao weldmentDao;

    @Override
    public void add(Weldment t) {
        weldmentDao.insert(t);
    }

    @Override
    public void addList(List<Weldment> list) {
        weldmentDao.insertList(list);
    }

    @Override
    public int update(Weldment t) {
        return weldmentDao.update(t);
    }

    @Override
    public Weldment getById(Long id) {
        return weldmentDao.getById(id);
    }

    @Override
    public Integer deleteById(Long id) {
        return weldmentDao.deleteById(id);
    }

    @Override
    public Long getCount(BaseQueryBo queryBo) {
        return weldmentDao.getCount(queryBo.getMap());
    }

    @Override
    public List<Weldment> getList(BaseQueryBo queryBo) {
        return weldmentDao.getList(queryBo.getMap());
    }

    @Override
    public List<Weldment> getListByIds(Set ids) {
        if (ids.isEmpty()) {
            return new ArrayList<>();
        }
        WeldmentQueryBo queryBo = new WeldmentQueryBo();
        queryBo.setDeleted(Constants.DELETED_NO);
        queryBo.setIds(ids);
        List<Weldment> list = this.getList(queryBo);
        return list;
    }

    @Override
    public Map<Long, Weldment> getMapByIds(Set ids) {
        if (ids.isEmpty()) {
            return new HashMap<>();
        }
        Map<Long, Weldment> map = new HashMap<>();
        List<Weldment> list = getListByIds(ids);
        for (Weldment entity : list) {
            map.put(entity.getId(), entity);
        }
        return map;
    }

}
