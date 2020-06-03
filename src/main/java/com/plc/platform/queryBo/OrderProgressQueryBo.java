package com.plc.platform.queryBo;


import java.util.Set;

/**
*  where 查询自定义封装，
*/
public class OrderProgressQueryBo extends BaseQueryBo {
        public void setIds(Set<Long> ids) {
           put("ids", ids);
        }

        public void setId(Long id) {
           put("id", id);
        }

        public void setOrderCode(String orderCode) {
           put("orderCode", orderCode);
        }

        public void setProductTime(java.util.Date productTime) {
           put("productTime", productTime);
        }

        public void setCreateTime(java.util.Date createTime) {
           put("createTime", createTime);
        }

        public void setModifyTime(java.util.Date modifyTime) {
           put("modifyTime", modifyTime);
        }

        public void setDeleted(Integer deleted) {
           put("deleted", deleted);
        }

        public void setFinishedProductCount(Integer finishedProductCount) {
           put("finishedProductCount", finishedProductCount);
        }

        public void setBadProductCount(Integer badProductCount) {
           put("badProductCount", badProductCount);
        }

        public void setMaterialName(String materialName) {
           put("materialName", materialName);
        }

        public void setMaterialCode(String materialCode) {
           put("materialCode", materialCode);
        }

        public void setSumCount(Integer sumCount) {
           put("sumCount", sumCount);
        }
}
