package com.plc.platform.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class OrderProgressUpdate {
    @NotBlank(message = "订单编号不能为空")
    private String orderCode;
    @NotNull(message = "生产日期能为空")
    private Long productTime;
    @NotNull(message = "良品数量不能为空")
    private Integer finishedProductCount;
    @NotNull(message = "不良品数量不能为空")
    private Integer badProductCount;



    @NotNull(message = "总数不能为空")
    private Integer sumCount;


    @NotBlank(message = "物料名称不能为空")
    private String materialName;

    @NotBlank(message = "物料编号不能为空")
    private String materialCode;
}
