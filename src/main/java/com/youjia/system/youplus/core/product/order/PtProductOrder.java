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
     * 对应的模板详情ID
     */
    private Long templateId;
    /**
     * 地勤人员
     */
    private Long groundPersonId;
    /**
     * 当前状态
     */
    private Integer state;
    /**
     * 子状态
     */
    private Integer childState;
    


}
