package com.plc.platform.dto.request;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductionPlanDto {


    private Long id;
    // 负责人
    @NotBlank(message = "负责人不能为空")
    private String leader;

    // 班次
    @NotNull(message = "班次不能为空")
    private Integer shift;

    // 生产日期
    @NotNull(message = "生产日期能为空")
    private Long productTime;

    @Valid
    private List<WeldmentDto> data;

    private Integer peopleCount;

    private Integer deviceCount;


    private List<Order> orderData;

    private List<MachineRunInfo> machineData;


    public static void main(String[] args) {
        ProductionPlanDto productionPlanDto = new ProductionPlanDto();
        productionPlanDto.setLeader("张三");
        productionPlanDto.setShift(1);
        productionPlanDto.setProductTime(System.currentTimeMillis());
        productionPlanDto.setData(new ArrayList<>());

        WeldmentDto weldmentDto = new WeldmentDto();
        weldmentDto.setConsumeCount(1);
        weldmentDto.setDrawingNo("drwingNO");
        weldmentDto.setRemark("rewe");
        weldmentDto.setPlanCount(1);
        weldmentDto.setSpecification("specification");
        weldmentDto.setRawMaterialName("rawMatttt");
        weldmentDto.setWeldmentName("weldmentName");
        productionPlanDto.getData().add(weldmentDto);
        System.out.println(JSON.toJSONString(productionPlanDto));
    }
}
