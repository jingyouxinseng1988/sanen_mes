package com.plc.platform.dto.request;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class AddProductionPlanDto {


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


    private Integer peopleCount;

    private Integer deviceCount;


    @NotNull(message = "产品名称不能为空")
    private String productName;

    @NotNull(message = "客户名称不能为空")
    private String customerName;

    private String tips;

    private List<MachineInfo> machineInfoList;

    private List<MaterialInfoDto> materialInfoList;

    private Long orderStartTime;
    private Long orderEndTime;
    private Integer orderSumCount;
    private Integer orderTodayPlanCount;


    public static void main(String[] args) {


        OrderDto orderDto = new OrderDto();
        orderDto.setCustomerName("王五");
        orderDto.setProductName("NBSDF_EDF");

        List<OrderDto> list = new ArrayList<>();
        list.add(orderDto);

        AddProductionPlanDto productionPlanDto = new AddProductionPlanDto();
        productionPlanDto.setLeader("张三");
        productionPlanDto.setShift(1);
        productionPlanDto.setProductTime(System.currentTimeMillis());
        productionPlanDto.setPeopleCount(11);
        productionPlanDto.setDeviceCount(34);


        System.out.println(JSON.toJSONString(productionPlanDto));
    }
}
