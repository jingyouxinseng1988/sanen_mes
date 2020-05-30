package com.plc.platform.entity;


import lombok.Data;

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
            private java.util.Date createTime;

        //  
            private java.util.Date modifyTime;

        // 物料信息 
            private String materialInfo;

        // 机器信息 
            private String machineInfo;


}
