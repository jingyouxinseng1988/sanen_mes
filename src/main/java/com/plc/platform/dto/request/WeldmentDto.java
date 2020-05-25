package com.plc.platform.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
public class WeldmentDto {


    private Long id;
    //
    @NotNull(message = "焊件名称能为空")
    private String weldmentName;

    // 计划数量
    @NotNull(message = "计划数量不能为空")
    private Integer planCount;

    // 备注
    private String remark;

    // 原料名称
    @NotBlank(message = "原料名称不能为空")
    private String rawMaterialName;


    @NotBlank(message = "图号不能为空")
    private String drawingNo;

    // 消耗数量
    @NotNull(message = "消耗数量不能为空")
    private Integer consumeCount;

    @NotBlank(message = "规格不能为空")
    private String specification;


}
