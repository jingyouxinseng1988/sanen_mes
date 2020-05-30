package com.plc.platform.queryBo;


import java.util.Set;

/**
*  where 查询自定义封装，
*/
public class OrderQueryBo extends BaseQueryBo {
        public void setIds(Set<Long> ids) {
           put("ids", ids);
        }

        public void setId(Long id) {
           put("id", id);
        }

        public void setProductName(String productName) {
           put("productName", productName);
        }

        public void setCustomerName(String customerName) {
           put("customerName", customerName);
        }

        public void setPlanId(Long planId) {
           put("planId", planId);
        }

        public void setDeleted(Integer deleted) {
           put("deleted", deleted);
        }

        public void setCreateTime(java.util.Date createTime) {
           put("createTime", createTime);
        }

        public void setModifyTime(java.util.Date modifyTime) {
           put("modifyTime", modifyTime);
        }
}
