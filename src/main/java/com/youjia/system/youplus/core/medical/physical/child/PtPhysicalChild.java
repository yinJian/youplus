package com.youjia.system.youplus.core.medical.physical.child;

import com.youjia.system.youplus.core.base.BaseDeleteEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pt_physical_child")
public class PtPhysicalChild extends BaseDeleteEntity {
    private Long physicalId;

    private String name;
    /**
     *联系人
     */
    private String contactPerson;
    private String province;
    private String city;
    private String country;

    private String address;

    private String workTime;

    private String phone;

    @Override
    public String toString() {
        return "PtPhysicalChild{" +
                "physicalId=" + physicalId +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", workTime='" + workTime + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public Long getPhysicalId() {
        return physicalId;
    }

    public void setPhysicalId(Long physicalId) {
        this.physicalId = physicalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
