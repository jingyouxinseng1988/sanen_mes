package com.plc.platform.controller.app;//package com.plc.platform.controller.app;

import com.alibaba.fastjson.JSON;
import com.plc.platform.common.Constants;
import com.plc.platform.controller.BaseController;
import com.plc.platform.domain.AjaxResult;
import com.plc.platform.dto.request.*;
import com.plc.platform.entity.Order;
import com.plc.platform.entity.ProductionPlan;
import com.plc.platform.queryBo.OrderQueryBo;
import com.plc.platform.queryBo.ProductionPlanQueryBo;
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
        productionPlanQueryBo.setProductTime(new Date(planDto.getProductTime() / 1000 / (60 * 60 * 24)));
        productionPlanQueryBo.setShift(planDto.getShift());
        productionPlanQueryBo.setDeleted(Constants.DELETED_NO);
        List<ProductionPlan> list = productionPlanService.getList(productionPlanQueryBo);
        ProductionPlan productionPlan = new ProductionPlan();
        if (!list.isEmpty()) {
            productionPlan = list.get(0);
        }
        SpringUtil.copyNotNullProperties(planDto, productionPlan);
        productionPlan.setProductTime(new Date(planDto.getProductTime()));
        if (planDto.getId() == null) {
            productionPlanService.add(productionPlan);
        } else {
            productionPlan.setModifyTime(new Date());
            productionPlanService.update(productionPlan);
        }
        Order order = new Order();
        order.setPlanId(productionPlan.getId());
        order.setMachineInfo(JSON.toJSONString(planDto.getMachineInfoList()));
        order.setMaterialInfo(JSON.toJSONString(planDto.getMaterialInfoList()));
        order.setTips(planDto.getTips());
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
            data.add(orderDto);
        }
        return AjaxResult.success(data);
    }

    @RequestMapping(value = "/material_machine/list")
    public AjaxResult getMaterialInfo(@RequestBody @Valid OrderId orderId) {
        Map<String, Object> data = new HashMap<>();
        Order order = orderService.getById(orderId.getOrderId());
        if (order == null) {
            return AjaxResult.success(data);
        }

        List<MachineInfo> machineInfoList = JSON.parseArray(order.getMachineInfo(), MachineInfo.class);
        List<MaterialInfoDto> materialInfoDtoList = JSON.parseArray(order.getMaterialInfo(), MaterialInfoDto.class);


        data.put("machineInfo", machineInfoList);
        data.put("materialInfo", materialInfoDtoList);
        return AjaxResult.success(data);
    }
}
    
