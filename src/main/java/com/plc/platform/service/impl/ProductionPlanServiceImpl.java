package com.plc.platform.service.impl;

import com.plc.platform.common.Constants;
import com.plc.platform.dao.ProductionPlanDao;
import com.plc.platform.entity.ProductionPlan;
import com.plc.platform.queryBo.BaseQueryBo;
import com.plc.platform.queryBo.ProductionPlanQueryBo;
import com.plc.platform.service.ProductionPlanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service("productionPlanService")
public class ProductionPlanServiceImpl implements ProductionPlanService {

    @Resource
    private ProductionPlanDao productionPlanDao;

    @Override
    public void add(ProductionPlan t) {
        productionPlanDao.insert(t);
    }

    @Override
    public void addList(List<ProductionPlan> list) {
        productionPlanDao.insertList(list);
    }

    @Override
    public int update(ProductionPlan t) {
        return productionPlanDao.update(t);
    }

    @Override
    public ProductionPlan getById(Long id) {
        return productionPlanDao.getById(id);
    }

    @Override
    public Integer deleteById(Long id) {
        return productionPlanDao.deleteById(id);
    }

    @Override
    public Long getCount(BaseQueryBo queryBo) {
        return productionPlanDao.getCount(queryBo.getMap());
    }

    @Override
    public List<ProductionPlan> getList(BaseQueryBo queryBo) {
        return productionPlanDao.getList(queryBo.getMap());
    }

    @Override
    public List<ProductionPlan> getListByIds(Set ids) {
        if (ids.isEmpty()) {
            return new ArrayList<>();
        }
        ProductionPlanQueryBo queryBo = new ProductionPlanQueryBo();
        queryBo.setDeleted(Constants.DELETED_NO);
        queryBo.setIds(ids);
        List<ProductionPlan> list = this.getList(queryBo);
        return list;
    }

    @Override
    public Map<Long, ProductionPlan> getMapByIds(Set ids) {
        if (ids.isEmpty()) {
            return new HashMap<>();
        }
        Map<Long, ProductionPlan> map = new HashMap<>();
        List<ProductionPlan> list = getListByIds(ids);
        for (ProductionPlan entity : list) {
            map.put(entity.getId(), entity);
        }
        return map;
    }

}
