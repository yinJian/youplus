package com.youjia.system.youplus.global.bean.request;

public class DoctorListQueryModel extends BaseModel {
    private String name;
    /**
     * 职称
     */
    private String level;
    private String phone;
    private String province;
    private String city;
    private String country;
    private String dept1;
    private String dept2;

    private String paper;

    private String hospitalName;

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
        return "DoctorListQueryModel{" +
                "name='" + name + '\'' +
                ", level='" + level + '\'' +
                ", phone='" + phone + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", dept1='" + dept1 + '\'' +
                ", dept2='" + dept2 + '\'' +
                ", paper='" + paper + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
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

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getDept1() {
        return dept1;
    }

    public void setDept1(String dept1) {
        this.dept1 = dept1;
    }

    public String getDept2() {
        return dept2;
    }

    public void setDept2(String dept2) {
        this.dept2 = dept2;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
}
