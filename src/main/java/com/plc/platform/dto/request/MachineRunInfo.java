package com.plc.platform.dto.request;

import lombok.Data;

@Data
public class MachineRunInfo {

    private String name;
    private Integer runTime;
    private Integer deviceCount;
}
