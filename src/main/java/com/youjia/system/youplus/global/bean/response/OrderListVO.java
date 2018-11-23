package com.youjia.system.youplus.global.bean.response;

import java.util.Date;

/**
 * @author wuweifeng wrote on 2018/11/22.
 */
public class OrderListVO {
    private Long id;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 商品名字 TODO
     */
    private String goodsName;
    /**
     * 公司名字 TODO
     */
    private String companyName;
    /**
     * 证件号码
     */
    private String paper;
    /**
     * 电话号码
     */
    private String mobile;
    /**
     * 保单号
     */
    private String cardNum;
    /**
     * 优加服务 TODO
     */
    private String youServers;
    /**
     * 保单生效时间
     */
    private Date beginTime;
    /**
     * 保单时间
     */
    private Date endTime;
    /**
     * 地区，省市县 TODO
     */
    private String area;
    /**
     * 状态（0正常，1中止，2终止，3失效）
     */
    private Integer status;

    @Override
    public String toString() {
        return "OrderListVO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", paper='" + paper + '\'' +
                ", mobile='" + mobile + '\'' +
                ", cardNum='" + cardNum + '\'' +
                ", youServers='" + youServers + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", area='" + area + '\'' +
                ", status=" + status +
                '}';
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getYouServers() {
        return youServers;
    }

    public void setYouServers(String youServers) {
        this.youServers = youServers;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
