package com.plc.platform.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderDto {

    private Long id;

    @NotNull(message = "产品名称不能为空")
    private String productName;

    @NotNull(message = "客户名称不能为空")
    private String customerName;


    //
    private Long planId;
}
