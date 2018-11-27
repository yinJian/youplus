package com.youjia.system.youplus.global.bean.request;

/**
 * @author wuweifeng wrote on 2018/11/26.
 */
public class GroundPersonListQueryModel extends BaseModel {
    private String userName;
    private String mobile;
    private String province;
    private String city;
    private String country;

    @Override
    public String toString() {
        return "GroundPersonListQueryModel{" +
                "userName='" + userName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
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
}