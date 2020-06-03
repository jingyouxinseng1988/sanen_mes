package com.plc.platform.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class OrderCode {
    @NotBlank(message = "订单Code不能为空")
    private String orderCode;
}
