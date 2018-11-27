package com.youjia.system.youplus.global.bean.response;

public class DoctorListVO {
    private Long id;
    /**
     * TODO
     */
    private String hospitalName;
    private String name;
    /**
     * 医院ID
     */
    private Long hospitalId;
    /**
     *科室
     */
    private String dept;
    /**
     * 一级科室
     */
    private String dept1;
    /**
     * 二级科室
     */
    private String dept2;

    private String province;
    private String city;
    private String country;
    private String phone;
    /**
     * 职称
     */
    private String level;
    /**
     * 头像
     */
    private String headIcon;
    /**
     * 简介
     */
    private String info;
    /**
     * 擅长
     */
    private String skill;
    private String remark;

    @Override
    public String toString() {
        return "DoctorListVO{" +
                "id=" + id +
                ", hospitalName='" + hospitalName + '\'' +
                ", name='" + name + '\'' +
                ", hospitalId=" + hospitalId +
                ", dept='" + dept + '\'' +
                ", dept1='" + dept1 + '\'' +
                ", dept2='" + dept2 + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", level='" + level + '\'' +
                ", headIcon='" + headIcon + '\'' +
                ", info='" + info + '\'' +
                ", skill='" + skill + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}