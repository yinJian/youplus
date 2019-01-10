package com.youjia.system.youplus.core.product.order;

import com.youjia.system.youplus.core.base.BaseDeleteEntity;

import javax.persistence.Entity;

/**
 * 产品的派单订单信息
 * @author wuweifeng wrote on 2018/12/4.
 */
@Entity
public class PtProductOrder extends BaseDeleteEntity {
    /**
     * 对应的服务单ID
     */
    private Long orderId;
    /**
     * 产品ID，如"押金垫付"
     */
    private Long productId;
    /**
     * 对应的模板ID
     */
    private Long templateId;
    /**
     * 地勤人员
     */
    private Long groundPersonId;
    /**
     * 当前状态group=14.未派单，已派单，已接单，
     */
    private String state;
    /**
     * 子状态group=15。已转单，未转单
     */
    private String childState;
    /**
     * 派单备注
     */
    private String remark;
    /**
     * 关闭原因
     */
    private String reason;

    @Override
    public String toString() {
        return "PtProductOrder{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", templateId=" + templateId +
                ", groundPersonId=" + groundPersonId +
                ", state='" + state + '\'' +
                ", childState='" + childState + '\'' +
                ", remark='" + remark + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getGroundPersonId() {
        return groundPersonId;
    }

    public void setGroundPersonId(Long groundPersonId) {
        this.groundPersonId = groundPersonId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getChildState() {
        return childState;
    }

    public void setChildState(String childState) {
        this.childState = childState;
    }
}
