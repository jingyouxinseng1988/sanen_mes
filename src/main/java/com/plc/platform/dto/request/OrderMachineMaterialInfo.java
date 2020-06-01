package com.plc.platform.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class OrderMachineMaterialInfo {

    private Long orderId;
    private List<MachineInfo> machineInfoList;
    private List<MaterialInfoDto> materialInfoList;

    private Long orderStartTime;
    private Long orderEndTime;
    private Integer orderSumCount;
    private Integer orderTodayPlanCount;


}
