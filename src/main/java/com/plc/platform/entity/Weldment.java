package com.plc.platform.entity;


import lombok.Data;

/**
 * 实体对象：
 */
@Data
public class Weldment {


    // ~~~~实体属性
    //  [主键]
    private Long id;

    // 焊件名称
    private String weldmentName;

    // 计划数量
    private Integer planCount;

    // 备注
    private String remark;

    // 原料名称
    private String rawMaterialName;


    // 图号
    private String drawingNo;

    // 消耗数量
    private Integer consumeCount;

    // 规格
    private String specification;

    // 创建时间
    private java.util.Date createTime;

    // 修改时间
    private java.util.Date modifyTime;

    // 是否删除
    private Integer deleted;

    // 计划Id
    private Long planId;


    private Integer finishedProductCount;
    private Integer badProductCount;
    private String startTime;
    private String endTime;

    private String subMaterialInfo;


}
