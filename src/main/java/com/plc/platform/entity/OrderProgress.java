package com.plc.platform.entity;


import lombok.Data;

/**
 * 实体对象：
 */
@Data
public class OrderProgress {


    // ~~~~实体属性
    //  [主键]
    private Long id;

    //
    private String orderCode;

    //
    private java.util.Date productTime;

    //
    private java.util.Date createTime;

    //
    private java.util.Date modifyTime;

    //
    private Integer deleted;

    //
    private Integer finishedProductCount;

    //
    private Integer badProductCount;

    //
    private String materialName;

    //
    private String materialCode;

    //
    private Integer sumCount;


}
