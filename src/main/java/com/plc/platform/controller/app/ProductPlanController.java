package com.plc.platform.controller.app;//package com.plc.platform.controller.app;

import com.plc.platform.common.Constants;
import com.plc.platform.controller.BaseController;
import com.plc.platform.domain.AjaxResult;
import com.plc.platform.dto.request.ProductionPlanDto;
import com.plc.platform.dto.request.WeldmentDto;
import com.plc.platform.entity.ProductionPlan;
import com.plc.platform.entity.Weldment;
import com.plc.platform.queryBo.ProductionPlanQueryBo;
import com.plc.platform.service.ProductionPlanService;
import com.plc.platform.service.WeldmentService;
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
        productionPlanService.add(productionPlan);
        List<WeldmentDto> data = planDto.getData();
        for (WeldmentDto dto : data) {
            Weldment weldment = new Weldment();
            BeanUtils.copyProperties(dto, weldment);
            weldment.setPlanId(productionPlan.getId());
            weldmentService.add(weldment);
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
            productionPlanDto.setProductTime(plan.getProductTime().getTime());


            BeanUtils.copyProperties(plan, productionPlanDto);
            productionPlanDto.setData(new ArrayList<>());
            List<Weldment> weldments = planMapWeldmentList.get(plan.getId());
            if (weldments == null) {
                continue;
            }
            for (Weldment weldment : weldments) {
                WeldmentDto weldmentDto = new WeldmentDto();
                BeanUtils.copyProperties(weldment, weldmentDto);
                productionPlanDto.getData().add(weldmentDto);
            }
        }

        return AjaxResult.success(data);
    }

}
