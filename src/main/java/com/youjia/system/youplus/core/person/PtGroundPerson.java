package com.youjia.system.youplus.core.person;

import com.youjia.system.youplus.core.base.BaseDeleteEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 地勤人员
 *
 * @author wuweifeng wrote on 2018/11/16.
 */
@Entity
@Table(name = "pt_ground_person")
public class PtGroundPerson extends BaseDeleteEntity {
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 男1女0
     */
    private Integer sex;
    /**
     * 证件号码
     */
    private String paper;
    /**
     * 电话号码
     */
    private String mobile;
    /**
     * 银行账户                                                                                                    
     */
    private String bankCardNum;
    /**
     * 银行名称
     */
    private String bank;
    
    private String province;
    private String city;
    private String country;
    /**
     * 地区负责人
     */
    private boolean areaOwner;
    /**
     * 医院id集合，逗号分隔
     */
    private String hospitalIds;
    /**
     * group=5key的集合，人员技能标签
     */
    private String skills;
    /**
     * 排班，周一上下午，周二上下午之类的。1，2是周一上下午，3，4周二上下午
     */
    private String workTimes;

    private String remark;

    @Override
    public String toString() {
        return "PtGroundPerson{" +
                "userName='" + userName + '\'' +
                ", sex=" + sex +
                ", paper='" + paper + '\'' +
                ", mobile='" + mobile + '\'' +
                ", bankCardNum='" + bankCardNum + '\'' +
                ", bank='" + bank + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", areaOwner=" + areaOwner +
                ", hospitalIds='" + hospitalIds + '\'' +
                ", skills='" + skills + '\'' +
                ", workTimes='" + workTimes + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getSex() {
        return sex;
    }

    public String getBankCardNum() {
        return bankCardNum;
    }

    public void setBankCardNum(String bankCardNum) {
        this.bankCardNum = bankCardNum;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isAreaOwner() {
        return areaOwner;
    }

    public void setAreaOwner(boolean areaOwner) {
        this.areaOwner = areaOwner;
    }

    public String getHospitalIds() {
        return hospitalIds;
    }

    public void setHospitalIds(String hospitalIds) {
        this.hospitalIds = hospitalIds;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getWorkTimes() {
        return workTimes;
    }

    public void setWorkTimes(String workTimes) {
        this.workTimes = workTimes;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
