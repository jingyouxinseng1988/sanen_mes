package com.plc.platform.service.impl;

import javax.annotation.Resource;

import com.plc.platform.queryBo.BaseQueryBo;
import com.plc.platform.queryBo.OrderProgressQueryBo;
import org.springframework.stereotype.Service;
import com.plc.platform.common.Constants;

import com.plc.platform.dao.OrderProgressDao;
import com.plc.platform.entity.OrderProgress;
import com.plc.platform.service.OrderProgressService;
import java.util.*;



@Service("orderProgressService")
public class OrderProgressServiceImpl  implements OrderProgressService {

	@Resource
	private OrderProgressDao orderProgressDao;

	@Override
	public void add(OrderProgress t) {
       orderProgressDao.insert(t);
	}

    @Override
    public void addList(List<OrderProgress> list) {
       orderProgressDao.insertList(list);
    }

	@Override
	public int update(OrderProgress t) {
	   return orderProgressDao.update(t);
	}

	@Override
	public OrderProgress getById(Long id) {
	   return orderProgressDao.getById(id);
	}

	@Override
	public Integer deleteById(Long id) {
	return orderProgressDao.deleteById(id);
	}

	@Override
	public Long getCount(BaseQueryBo queryBo) {
	return orderProgressDao.getCount(queryBo.getMap());
	}

	@Override
	public List<OrderProgress> getList(BaseQueryBo queryBo) {
		return orderProgressDao.getList(queryBo.getMap());
	}

    @Override
    public List<OrderProgress> getListByIds(Set ids) {
            if (ids.isEmpty()) {
              return new ArrayList<>();
            }
            OrderProgressQueryBo queryBo = new OrderProgressQueryBo();
            queryBo.setDeleted(Constants.DELETED_NO);
            queryBo.setIds(ids);
            List<OrderProgress> list = this.getList(queryBo);
            return list;
    }

   @Override
   public Map<Long, OrderProgress> getMapByIds(Set ids) {
        if (ids.isEmpty()) {
         return new HashMap<>();
        }
        Map<Long, OrderProgress> map = new HashMap<>();
        List<OrderProgress> list = getListByIds(ids);
        for (OrderProgress entity : list) {
            map.put(entity.getId(), entity);
        }
       return map;
    }

}
