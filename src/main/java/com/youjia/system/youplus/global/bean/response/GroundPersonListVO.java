package com.youjia.system.youplus.global.bean.response;

/**
 * @author wuweifeng wrote on 2018/11/26.
 */
public class GroundPersonListVO {
    private Long id;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 电话号码
     */
    private String mobile;
    private String province;
    private String city;
    private String country;
    private String remark;

    @Override
    public String toString() {
        return "GroundPersonListVO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", remark='" + remark + '\'' +
                '}';
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
