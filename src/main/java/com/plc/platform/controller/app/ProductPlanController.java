package com.plc.platform.controller.app;//package com.plc.platform.controller.app;

import com.alibaba.fastjson.JSON;
import com.plc.platform.common.Constants;
import com.plc.platform.controller.BaseController;
import com.plc.platform.domain.AjaxResult;
import com.plc.platform.dto.request.*;
import com.plc.platform.entity.ProductionPlan;
import com.plc.platform.entity.Weldment;
import com.plc.platform.queryBo.ProductionPlanQueryBo;
import com.plc.platform.queryBo.WeldmentQueryBo;
import com.plc.platform.service.ProductionPlanService;
import com.plc.platform.service.WeldmentService;
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
    private WeldmentService weldmentService;


    /**
     * 添加
     *
     * @return
     */
    @RequestMapping(value = "/add")
    @Transactional
    public AjaxResult add(@RequestBody @Valid ProductionPlanDto planDto) {
        planDto.setId(null);
        ProductionPlan productionPlan = new ProductionPlan();

        BeanUtils.copyProperties(planDto, productionPlan);
        productionPlan.setProductTime(new Date(planDto.getProductTime()));
        productionPlan.setMachineData(JSON.toJSONString(planDto.getMachineData()));
        productionPlan.setOrderData(JSON.toJSONString(planDto.getOrderData()));
        productionPlanService.add(productionPlan);
        List<WeldmentDto> data = planDto.getData();
        for (WeldmentDto dto : data) {
            dto.setId(null);
            Weldment weldment = new Weldment();
            BeanUtils.copyProperties(dto, weldment);
            weldment.setSubMaterialInfo(JSON.toJSONString(dto.getSubMaterialInfo()));
            weldment.setPlanId(productionPlan.getId());
            weldmentService.add(weldment);
        }
        return AjaxResult.success("");
    }


    @RequestMapping(value = "/update")
    @Transactional
    public AjaxResult update(@RequestBody ProductionPlanDto planDto) {

        ProductionPlan productionPlan = productionPlanService.getById(planDto.getId());
        if(productionPlan==null){
            return AjaxResult.warn("没有找到该数据");
        }
        SpringUtil.copyNotNullProperties(planDto, productionPlan);
        productionPlan.setProductTime(new Date(planDto.getProductTime()));
        productionPlan.setMachineData(JSON.toJSONString(planDto.getMachineData()));
        productionPlan.setOrderData(JSON.toJSONString(planDto.getOrderData()));
        productionPlanService.update(productionPlan);


        WeldmentQueryBo weldmentQueryBo = new WeldmentQueryBo();
        weldmentQueryBo.setPlanId(productionPlan.getId());
        weldmentQueryBo.setDeleted(Constants.DELETED_NO);
        Map<Long, Weldment> idMap = weldmentService.getList(weldmentQueryBo).stream()
                .collect(Collectors.toMap(Weldment::getId, X -> X));

        List<WeldmentDto> data = planDto.getData();
        for (WeldmentDto dto : data) {
            Weldment weldment = idMap.get(dto.getId());
            if (weldment == null) {
                weldment = new Weldment();
                SpringUtil.copyNotNullProperties(dto, weldment);
                weldment.setSubMaterialInfo(JSON.toJSONString(dto.getSubMaterialInfo()));
                weldment.setPlanId(productionPlan.getId());
                weldmentService.add(weldment);
            }else {
                SpringUtil.copyNotNullProperties(dto, weldment);
                weldment.setSubMaterialInfo(JSON.toJSONString(dto.getSubMaterialInfo()));
                weldment.setPlanId(productionPlan.getId());
                weldmentService.update(weldment);
            }
        }
        return AjaxResult.success("");
    }

    @RequestMapping(value = "/list")
    public AjaxResult get(ProductionPlanDto dto) {

        ProductionPlanQueryBo productionPlanQueryBo = new ProductionPlanQueryBo();
        productionPlanQueryBo.setDeleted(Constants.DELETED_NO);
        if (dto.getProductTime() != null) {
            productionPlanQueryBo.setProductTime(new Date(dto.getProductTime()));
        }
        productionPlanQueryBo.setLeader(dto.getLeader());
        productionPlanQueryBo.setShift(dto.getShift());
        productionPlanQueryBo.setSort("id", "desc");
        List<ProductionPlan> list = productionPlanService.getList(productionPlanQueryBo);
        Set<Long> ids = new HashSet<>();
        for (ProductionPlan plan : list) {
            ids.add(plan.getId());
        }
        List<Weldment> weldmentList = weldmentService.getListByIds(ids);
        Map<Long, List<Weldment>> planMapWeldmentList = weldmentList.stream().collect(Collectors.groupingBy(Weldment::getPlanId));
        List<Object> data = new ArrayList<>();
        for (ProductionPlan plan : list) {
            ProductionPlanDto productionPlanDto = new ProductionPlanDto();
            data.add(productionPlanDto);
            BeanUtils.copyProperties(plan, productionPlanDto);
            productionPlanDto.setProductTime(plan.getProductTime().getTime());
            productionPlanDto.setMachineData(JSON.parseArray(plan.getMachineData(), MachineRunInfo.class));
            productionPlanDto.setOrderData(JSON.parseArray(plan.getOrderData(), Order.class));
            productionPlanDto.setData(new ArrayList<>());
            List<Weldment> weldments = planMapWeldmentList.get(plan.getId());
            if (weldments == null) {
                continue;
            }
            for (Weldment weldment : weldments) {
                WeldmentDto weldmentDto = new WeldmentDto();
                BeanUtils.copyProperties(weldment, weldmentDto);
                weldmentDto.setSubMaterialInfo(JSON.parseArray(weldment.getSubMaterialInfo(), SubMaterialInfo.class));
                productionPlanDto.getData().add(weldmentDto);
            }
        }

        return AjaxResult.success(data);
    }

}
