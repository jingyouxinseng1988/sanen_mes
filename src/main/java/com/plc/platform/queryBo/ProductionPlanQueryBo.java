package com.plc.platform.queryBo;


import java.util.Set;

/**
*  where 查询自定义封装，
*/
public class ProductionPlanQueryBo extends BaseQueryBo {
        public void setIds(Set<Long> ids) {
           put("ids", ids);
        }

        public void setId(Long id) {
           put("id", id);
        }

        public void setCreateTime(java.util.Date createTime) {
           put("createTime", createTime);
        }

        public void setLeader(String leader) {
           put("leader", leader);
        }

        public void setShift(Integer shift) {
           put("shift", shift);
        }

        public void setModifyTime(java.util.Date modifyTime) {
           put("modifyTime", modifyTime);
        }

        public void setDeleted(Integer deleted) {
           put("deleted", deleted);
        }

        public void setProductTime(java.util.Date productTime) {
           put("productTime", productTime);
        }
}
