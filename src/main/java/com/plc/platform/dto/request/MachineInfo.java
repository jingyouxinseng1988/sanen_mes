package com.plc.platform.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MachineInfo {

    private String name;
    private Integer runTime;
    private Integer deviceCount;
}
