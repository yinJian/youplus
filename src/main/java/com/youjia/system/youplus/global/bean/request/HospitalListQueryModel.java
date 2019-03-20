package com.youjia.system.youplus.global.bean.request;

public class HospitalListQueryModel extends BaseModel {
    private String name;
    private String level;
    private String province;
    private String city;
    private String country;

    /**
     * 注册
     */
    private Boolean register;
    /**
     * 认证
     */
    private Boolean authentication;
    /**
     * 优选
     */
    private Boolean choose;

    @Override
    public String toString() {
        return "HospitalListQueryModel{" +
                "name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", register=" + register +
                ", authentication=" + authentication +
                ", choose=" + choose +
                '}';
    }

    public Boolean getRegister() {
        return register;
    }

    public void setRegister(Boolean register) {
        this.register = register;
    }

    public Boolean getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Boolean authentication) {
        this.authentication = authentication;
    }

    public Boolean getChoose() {
        return choose;
    }

    public void setChoose(Boolean choose) {
        this.choose = choose;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
