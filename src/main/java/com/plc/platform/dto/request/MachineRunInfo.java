package com.plc.platform.dto.request;

import lombok.Data;

@Data
public class MachineRunInfo {

    private String name;
    private String runTime;
    private String deviceCount;
}
