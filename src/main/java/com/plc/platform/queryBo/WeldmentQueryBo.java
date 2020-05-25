package com.plc.platform.queryBo;


import java.util.Set;

/**
 * where 查询自定义封装，
 */
public class WeldmentQueryBo extends BaseQueryBo {
    public void setIds(Set<Long> ids) {
        put("ids", ids);
    }

    public void setId(Long id) {
        put("id", id);
    }

    public void setWeldmentName(String weldmentName) {
        put("weldmentName", weldmentName);
    }

    public void setPlanCount(Integer planCount) {
        put("planCount", planCount);
    }

    public void setRemark(String remark) {
        put("remark", remark);
    }

    public void setRawMaterialName(String rawMaterialName) {
        put("rawMaterialName", rawMaterialName);
    }

    public void setDrawingNo(String drawingNo) {
        put("drawingNo", drawingNo);
    }

    public void setConsumeCount(Integer consumeCount) {
        put("consumeCount", consumeCount);
    }

    public void setSpecification(String specification) {
        put("specification", specification);
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

    public void setPlanId(Long planId) {
        put("planId", planId);
    }
}
