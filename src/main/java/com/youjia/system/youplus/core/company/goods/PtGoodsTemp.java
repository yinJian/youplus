package com.youjia.system.youplus.core.company.goods;

import com.youjia.system.youplus.core.base.BaseDeleteEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 商品
 *
 * @author wuweifeng wrote on 2018/11/16.
 */
@Entity
@Table(name = "pt_goods_temp")
public class PtGoodsTemp extends BaseDeleteEntity {
    /**
     * 对应compnay的主键
     */
    private Long goodsId;
    /**
     * 修改原因
     */
    private String reason;
    ///////////// PtGoods ///////////////////////////////////
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品计划ID
     */
    private Long ptGoodsPlanId;
    /**
     * 商品生效时间
     */
    private Date beginTime;
    /**
     * 失效时间
     */
    private Date endTime;
    /**
     * 绑定优加服务，key=8
     */
    private String youPlusService;
    /**
     * 状态（-1待审核，0正常，-2被拒绝）
     */
    private Integer status;
    private String remark;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPtGoodsPlanId() {
        return ptGoodsPlanId;
    }

    public void setPtGoodsPlanId(Long ptGoodsPlanId) {
        this.ptGoodsPlanId = ptGoodsPlanId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getYouPlusService() {
        return youPlusService;
    }

    public void setYouPlusService(String youPlusService) {
        this.youPlusService = youPlusService;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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
}
