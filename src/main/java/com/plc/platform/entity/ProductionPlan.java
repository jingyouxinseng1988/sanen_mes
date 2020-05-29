package com.plc.platform.entity;


import lombok.Data;

import java.util.Date;

/**
 * 实体对象：
 */
@Data
public class ProductionPlan {


    // ~~~~实体属性
    //  [主键]
    private Long id;

    // 创建时间
    private Date createTime;
    private Date modifyTime;

    // 删除
    private Integer deleted;

    // 负责人
    private String leader;

    // 班次
    private Integer shift;

    // 修改时间


    // 生产日期
    private Date productTime;


    private Integer peopleCount;

    private Integer deviceCount;


    private String machineData;

    private String orderData;


}
