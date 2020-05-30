package com.plc.platform.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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


    private Integer peopleCount;

    private Integer deviceCount;


    public static void main(String[] args) {

//        Order order = new Order();
//        order.setCustomerName("testCustomerName");
//        order.setProductName("productKeyNae");
//        List<Order> orders = new ArrayList<>();
//        orders.add(order);
//
//        List<MachineRunInfo> data = new ArrayList<>();
//        MachineRunInfo machineRunInfo = new MachineRunInfo();
//        machineRunInfo.setName("test");
//        machineRunInfo.setDeviceCount(1);
//        machineRunInfo.setRunTime(1);
//        data.add(machineRunInfo);
//
//
//        ProductionPlanDto productionPlanDto = new ProductionPlanDto();
//        productionPlanDto.setLeader("张三");
//        productionPlanDto.setShift(1);
//        productionPlanDto.setProductTime(System.currentTimeMillis());
//        productionPlanDto.setData(new ArrayList<>());
//        productionPlanDto.setMachineData(data);
//        productionPlanDto.setOrderData(orders);
//        WeldmentDto weldmentDto = new WeldmentDto();
//        weldmentDto.setConsumeCount(1);
//        weldmentDto.setDrawingNo("drwingNO");
//        weldmentDto.setRemark("rewe");
//        weldmentDto.setPlanCount(1);
//        weldmentDto.setSpecification("specification");
//        weldmentDto.setRawMaterialName("rawMatttt");
//        weldmentDto.setWeldmentName("weldmentName");
//
//        List<SubMaterialInfo> suInfoList = new ArrayList<>();
//        SubMaterialInfo subMaterialInfo = new SubMaterialInfo();
//        subMaterialInfo.setMaterialCode("123456");
//        subMaterialInfo.setMaterialCount(120);
//        subMaterialInfo.setMaterialName("测试物料名称");
//        suInfoList.add(subMaterialInfo);
//
//        weldmentDto.setSubMaterialInfo(suInfoList);
//        weldmentDto.setEndTime("20200529");
//        weldmentDto.setStartTime("20202586");
//        productionPlanDto.getData().add(weldmentDto);
//        System.out.println(JSON.toJSONString(productionPlanDto));
    }
}
