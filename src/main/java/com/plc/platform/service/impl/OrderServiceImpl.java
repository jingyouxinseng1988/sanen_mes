package com.plc.platform.service.impl;

import com.plc.platform.common.Constants;
import com.plc.platform.dao.OrderDao;
import com.plc.platform.entity.Order;
import com.plc.platform.queryBo.BaseQueryBo;
import com.plc.platform.queryBo.OrderQueryBo;
import com.plc.platform.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;


@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Override
    public void add(Order t) {
        orderDao.insert(t);
    }

    @Override
    public void addList(List<Order> list) {
        orderDao.insertList(list);
    }

    @Override
    public int update(Order t) {
        return orderDao.update(t);
    }

    @Override
    public Order getById(Long id) {
        return orderDao.getById(id);
    }

    @Override
    public Integer deleteById(Long id) {
        return orderDao.deleteById(id);
    }

    @Override
    public Long getCount(BaseQueryBo queryBo) {
        return orderDao.getCount(queryBo.getMap());
    }

    @Override
    public List<Order> getList(BaseQueryBo queryBo) {
        return orderDao.getList(queryBo.getMap());
    }

    @Override
    public List<Order> getListByIds(Set ids) {
        if (ids.isEmpty()) {
            return new ArrayList<>();
        }
        OrderQueryBo queryBo = new OrderQueryBo();
        queryBo.setDeleted(Constants.DELETED_NO);
        queryBo.setIds(ids);
        List<Order> list = this.getList(queryBo);
        return list;
    }


    @Override
    public Map<Long, Order> getMapByIds(Set ids) {
        if (ids.isEmpty()) {
            return new HashMap<>();
        }
        Map<Long, Order> map = new HashMap<>();
        List<Order> list = getListByIds(ids);
        for (Order entity : list) {
            map.put(entity.getId(), entity);
        }
        return map;
    }


}
