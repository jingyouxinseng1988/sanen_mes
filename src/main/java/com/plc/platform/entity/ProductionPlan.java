package com.plc.platform.entity;


import lombok.Data;

/**
 * 实体对象：
 */
@Data
public class ProductionPlan {


    // ~~~~实体属性
    //  [主键]
    private Long id;

    // 创建时间
    private java.util.Date createTime;
    private java.util.Date modifyTime;

    // 删除
    private Integer deleted;

    // 负责人
    private String leader;

    // 班次
    private Integer shift;

    // 修改时间


    // 生产日期
    private java.util.Date productTime;


}
