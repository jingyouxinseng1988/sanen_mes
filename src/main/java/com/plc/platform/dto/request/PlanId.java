package com.plc.platform.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PlanId {
    @NotNull(message = "计划班次Id不能为空")
    private Long planId;
}
