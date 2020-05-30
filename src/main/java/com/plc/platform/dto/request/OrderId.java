package com.plc.platform.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderId {
    @NotNull(message = "订单Id不能为空")
    private Long orderId;
}
