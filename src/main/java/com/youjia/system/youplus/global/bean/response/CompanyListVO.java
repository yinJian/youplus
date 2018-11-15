package com.youjia.system.youplus.global.bean.response;

import java.util.Date;

/**
 * @author wuweifeng wrote on 2018/11/15.
 */
public class CompanyListVO {
    private Long id;
    /**
     * 公司名称
     */
    private String name;
    /**
     * 公司地址
     */
    private String address;
    /**
     * 公司性质，"国企，外商，民营"，group=6
     */
    private String property;
    /**
     * 公司类型，"保险公司，互联网公司"，group=7
     */
    private String type;
    /**
     * 是否上市
     */
    private boolean ipo;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 入驻时间
     */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isIpo() {
        return ipo;
    }

    public void setIpo(boolean ipo) {
        this.ipo = ipo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
