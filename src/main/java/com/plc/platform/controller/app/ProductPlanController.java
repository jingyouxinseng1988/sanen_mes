package com.plc.platform.controller.app;//package com.plc.platform.controller.app;

import com.alibaba.fastjson.JSON;
import com.plc.platform.common.Constants;
import com.plc.platform.controller.BaseController;
import com.plc.platform.domain.AjaxResult;
import com.plc.platform.dto.request.*;
import com.plc.platform.entity.Order;
import com.plc.platform.entity.OrderProgress;
import com.plc.platform.entity.ProductionPlan;
import com.plc.platform.queryBo.OrderProgressQueryBo;
import com.plc.platform.queryBo.OrderQueryBo;
import com.plc.platform.queryBo.ProductionPlanQueryBo;
import com.plc.platform.service.OrderProgressService;
import com.plc.platform.service.OrderService;
import com.plc.platform.service.ProductionPlanService;
import com.plc.platform.util.SpringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/production_plan")
public class ProductPlanController extends BaseController {
    @Resource
    private ProductionPlanService productionPlanService;
    @Resource
    private OrderService orderService;

    /**
     * 添加
     *
     * @return
     */
    @RequestMapping(value = "/add")
    @Transactional
    public AjaxResult add(@RequestBody @Valid AddProductionPlanDto planDto) {
        planDto.setId(null);
        ProductionPlanQueryBo productionPlanQueryBo = new ProductionPlanQueryBo();
        productionPlanQueryBo.setProductTime(new Date(planDto.getProductTime()));
        productionPlanQueryBo.setShift(planDto.getShift());
        productionPlanQueryBo.setDeleted(Constants.DELETED_NO);
        List<ProductionPlan> list = productionPlanService.getList(productionPlanQueryBo);
        ProductionPlan productionPlan = new ProductionPlan();
        if (!list.isEmpty()) {
            productionPlan = list.get(0);
        }
        SpringUtil.copyNotNullProperties(planDto, productionPlan);
        productionPlan.setProductTime(new Date(planDto.getProductTime()));
        if (productionPlan.getId() == null) {
            productionPlanService.add(productionPlan);
        } else {
            productionPlan.setModifyTime(new Date());
            productionPlanService.update(productionPlan);
        }
        Order order = new Order();
        order.setOrderCode(planDto.getOrderCode());
        order.setPlanId(productionPlan.getId());
        order.setProductName(planDto.getProductName());
        order.setCustomerName(planDto.getCustomerName());
        order.setMachineInfo(JSON.toJSONString(planDto.getMachineInfoList()));
        order.setMaterialInfo(JSON.toJSONString(planDto.getMaterialInfoList()));
        order.setTips(planDto.getTips());
        if (planDto.getOrderEndTime() != null) {
            order.setOrderStartTime(new Date(planDto.getOrderStartTime()));
        }
        if (planDto.getOrderStartTime() != null) {
            order.setOrderEndTime(new Date(planDto.getOrderEndTime()));
        }
        order.setOrderTodayPlanCount(planDto.getOrderTodayPlanCount());
        order.setOrderSumCount(planDto.getOrderSumCount());
        orderService.add(order);
        return AjaxResult.success("");
    }


    @RequestMapping(value = "/update")
    @Transactional
    public AjaxResult update(@RequestBody MachineMaterialInfo info) {

        Order order = orderService.getById(info.getOrderId());
        if (order == null) {
            return AjaxResult.warn("没有找到该订单");
        }
        order.setMachineInfo(JSON.toJSONString(info.getMachineInfoList()));
        order.setMaterialInfo(JSON.toJSONString(info.getMaterialInfoList()));
        order.setModifyTime(new Date());
        orderService.update(order);
        return AjaxResult.success("");
    }

    @RequestMapping(value = "/list")
    public AjaxResult get(@RequestBody ProductionPlanDto dto) {
        ProductionPlanQueryBo productionPlanQueryBo = new ProductionPlanQueryBo();
        productionPlanQueryBo.setShift(dto.getShift());
        productionPlanQueryBo.setLeader(dto.getLeader());
        productionPlanQueryBo.setDeleted(Constants.DELETED_NO);
        if (dto.getProductTime() != null) {
            productionPlanQueryBo.setProductTime(new Date(dto.getProductTime()));
        }
        productionPlanQueryBo.setLeader(dto.getLeader());
        productionPlanQueryBo.setShift(dto.getShift());
        productionPlanQueryBo.setSort("id", "desc");
        List<ProductionPlan> list = productionPlanService.getList(productionPlanQueryBo);
        List<Object> data = new ArrayList<>();
        for (ProductionPlan plan : list) {
            ProductionPlanDto productionPlanDto = new ProductionPlanDto();
            data.add(productionPlanDto);
            BeanUtils.copyProperties(plan, productionPlanDto);
            productionPlanDto.setProductTime(plan.getProductTime().getTime());
        }

        return AjaxResult.success(data);
    }

    @RequestMapping(value = "/order/list")
    public AjaxResult getOrderList(@RequestBody @Valid PlanId planId) {
        OrderQueryBo orderQueryBo = new OrderQueryBo();
        orderQueryBo.setPlanId(planId.getPlanId());
        orderQueryBo.setDeleted(Constants.DELETED_NO);
        List<Order> list = orderService.getList(orderQueryBo);
        List<Object> data = new ArrayList<>();
        for (Order order : list) {
            OrderDto orderDto = new OrderDto();
            SpringUtil.copyNotNullProperties(order, orderDto);
            if (order.getOrderStartTime() != null) {
                orderDto.setOrderEndTime(order.getOrderStartTime().getTime());
            }
            if (order.getOrderEndTime() != null) {
                orderDto.setOrderStartTime(order.getOrderEndTime().getTime());
            }
            orderDto.setOrderCode(order.getOrderCode());
            data.add(orderDto);
        }
        return AjaxResult.success(data);
    }

    @RequestMapping(value = "/material_machine/list")
    public AjaxResult getMaterialInfo(@RequestBody @Valid OrderId orderId) {
        OrderMachineMaterialInfo orderMachineMaterialInfo = new OrderMachineMaterialInfo();
        Order order = orderService.getById(orderId.getOrderId());
        if (order == null) {
            return AjaxResult.success(orderMachineMaterialInfo);
        }

        List<MachineInfo> machineInfoList = JSON.parseArray(order.getMachineInfo(), MachineInfo.class);
        List<MaterialInfoDto> materialInfoDtoList = JSON.parseArray(order.getMaterialInfo(), MaterialInfoDto.class);

        orderMachineMaterialInfo.setMachineInfoList(machineInfoList);
        orderMachineMaterialInfo.setMaterialInfoList(materialInfoDtoList);

        orderMachineMaterialInfo.setOrderId(order.getId());
        if (order.getOrderEndTime() != null) {
            orderMachineMaterialInfo.setOrderEndTime(order.getOrderEndTime().getTime());
        }
        if (order.getOrderStartTime() != null) {
            orderMachineMaterialInfo.setOrderStartTime(order.getOrderStartTime().getTime());
        }


        orderMachineMaterialInfo.setOrderSumCount(order.getOrderSumCount());
        orderMachineMaterialInfo.setOrderTodayPlanCount(order.getOrderTodayPlanCount());
        orderMachineMaterialInfo.setOrderCode(order.getOrderCode());
        return AjaxResult.success(orderMachineMaterialInfo);
    }

    @Resource
    private OrderProgressService orderProgressService;


    @RequestMapping(value = "/orderProgress/update")
    public AjaxResult orderProgressUpdate(@RequestBody @Valid List<OrderProgressUpdate> orderProgressUpdateList) {

        for (OrderProgressUpdate orderProgressUpdate : orderProgressUpdateList) {
            Date producTime = new Date(orderProgressUpdate.getProductTime());
            OrderProgressQueryBo orderProgressQueryBo = new OrderProgressQueryBo();
            orderProgressQueryBo.setDeleted(Constants.DELETED_NO);
            orderProgressQueryBo.setProductTime(producTime);
            orderProgressQueryBo.setMaterialCode(orderProgressUpdate.getMaterialCode());
            orderProgressQueryBo.setOrderCode(orderProgressUpdate.getOrderCode());

            List<OrderProgress> list = orderProgressService.getList(orderProgressQueryBo);
            if (list.isEmpty()) {
                OrderProgress orderProgress = new OrderProgress();
                orderProgress.setBadProductCount(orderProgressUpdate.getBadProductCount());
                orderProgress.setFinishedProductCount(orderProgressUpdate.getFinishedProductCount());
                orderProgress.setProductTime(producTime);
                orderProgress.setOrderCode(orderProgressUpdate.getOrderCode());
                orderProgress.setSumCount(orderProgressUpdate.getSumCount());
                orderProgress.setMaterialCode(orderProgressUpdate.getMaterialCode());
                orderProgress.setMaterialName(orderProgressUpdate.getMaterialName());
                orderProgressService.add(orderProgress);
            } else {
                OrderProgress orderProgress = list.get(0);
                orderProgress.setBadProductCount(orderProgress.getBadProductCount() + orderProgressUpdate.getBadProductCount());
                orderProgress.setFinishedProductCount(orderProgress.getFinishedProductCount() + orderProgressUpdate.getFinishedProductCount());
                orderProgress.setProductTime(producTime);
                orderProgress.setSumCount(orderProgressUpdate.getSumCount());
                orderProgress.setMaterialCode(orderProgressUpdate.getMaterialCode());
                orderProgress.setMaterialName(orderProgressUpdate.getMaterialName());
                orderProgressService.update(orderProgress);
            }
        }

        return AjaxResult.success("");

    }

    @RequestMapping(value = "/orderProgress/list")
    public AjaxResult getProgressList(@RequestBody @Valid OrderCode orderCode) {
        OrderProgressQueryBo orderProgressQueryBo = new OrderProgressQueryBo();
        orderProgressQueryBo.setDeleted(Constants.DELETED_NO);
        orderProgressQueryBo.setOrderCode(orderCode.getOrderCode());
        List<OrderProgress> list = orderProgressService.getList(orderProgressQueryBo);
        Map<String, List<OrderProgress>> collect = list.stream().collect(Collectors.groupingBy(OrderProgress::getMaterialCode));

        Map<String, Object> result = new HashMap<>();
        Iterator<Map.Entry<String, List<OrderProgress>>> iterator = collect.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<OrderProgress>> next = iterator.next();
            String key = next.getKey();
            List<OrderProgress> value = next.getValue();
            List<Object> data = new ArrayList<>();
            for (OrderProgress orderProgress : value) {
                Map<String, Object> map = new HashMap<>();
                map.put("productTime", orderProgress.getProductTime().getTime());
                map.put("orderCode", orderProgress.getOrderCode());
                map.put("badProductCount", orderProgress.getBadProductCount());
                map.put("finishedProductCount", orderProgress.getFinishedProductCount());
                map.put("sumCount", orderProgress.getSumCount());
                map.put("materialCode", orderProgress.getMaterialCode());
                map.put("materialName", orderProgress.getMaterialName());
                data.add(map);
            }
            result.put(key, data);
        }

        return AjaxResult.success(result);
    }


    @RequestMapping(value = "/distinctOrder/list")
    public AjaxResult getOrderList(@RequestBody OrderCode orderCode) {
        OrderQueryBo orderQueryBo = new OrderQueryBo();
        orderQueryBo.setDeleted(Constants.DELETED_NO);
        orderQueryBo.setSort("id", "desc");
        List<Order> list = orderService.getList(orderQueryBo);
        Map<String, Order> collect = new HashMap<>();
        for (Order order : list) {
            if (collect.containsKey(order.getOrderCode())) {
                continue;
            }
            collect.put(order.getOrderCode(), order);
        }
        List<Object> data = new ArrayList<>();
        Iterator<Map.Entry<String, Order>> iterator = collect.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Order> next = iterator.next();
            Order value = next.getValue();
            Map<String, Object> map = new HashMap<>();
            map.put("productName", value.getProductName());
            map.put("sumCount", value.getOrderSumCount());
            map.put("orderEndTime", value.getOrderEndTime() == null ? 0 : value.getOrderEndTime().getTime());
            map.put("orderStartTime", value.getOrderEndTime() == null ? 0 : value.getOrderStartTime().getTime());
            map.put("id", value.getId());
            map.put("customerName", value.getCustomerName());
            map.put("orderCode", value.getOrderCode());
            data.add(map);

        }
        return AjaxResult.success(data);
    }
}
    
