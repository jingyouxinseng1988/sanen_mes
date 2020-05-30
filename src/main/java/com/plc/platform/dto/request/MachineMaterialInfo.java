package com.plc.platform.dto.request;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class MachineMaterialInfo {

    @NotNull(message = "订单Id不能为空")
    private Long orderId;
    private List<MachineInfo> machineInfoList;
    private List<MaterialInfoDto> materialInfoList;

    public static void main(String[] args) {

        List<MaterialInfoDto> MaterialInfoList = new ArrayList<>();
        MaterialInfoDto weldmentDto = new MaterialInfoDto();
        weldmentDto.setConsumeCount(1);
        weldmentDto.setDrawingNo("drwingNO");
        weldmentDto.setRemark("rewe");
        weldmentDto.setPlanCount(1);
        weldmentDto.setSpecification("specification");
        weldmentDto.setRawMaterialName("rawMatttt");
        weldmentDto.setWeldmentName("weldmentName");

        MaterialInfoList.add(weldmentDto);

        MachineInfo machineInfo=new MachineInfo();
        machineInfo.setDeviceCount(234);
        machineInfo.setName("A机器");
        machineInfo.setRunTime(10);
        List<MachineInfo> mchineInfoList = new ArrayList<>();
        mchineInfoList.add(machineInfo);
        MachineMaterialInfo info=new MachineMaterialInfo();
        info.setMaterialInfoList(MaterialInfoList);
        info.setMachineInfoList(mchineInfoList);

        info.setOrderId(1L);
        System.out.println(JSON.toJSONString(info));


    }
}
