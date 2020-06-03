package com.plc.platform.entity;


import lombok.Data;

import java.util.Date;


/**
 * 实体对象：
 */
@Data
public class Order {


    // ~~~~实体属性
    //  [主键]
    private Long id;

    //
    private String productName;

    //
    private String customerName;

    //
    private Long planId;

    //
    private Integer deleted;

    //
    private Date createTime;

    //
    private Date modifyTime;

    // 物料信息
    private String materialInfo;

    // 机器信息
    private String machineInfo;

    private String tips;

    private String orderCode;


    private Date orderStartTime;
    private Date orderEndTime;
    private Integer orderSumCount;
    private Integer orderTodayPlanCount;


}
