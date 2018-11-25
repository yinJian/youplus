package com.youjia.system.youplus.core.medical.dentistry;

import com.youjia.system.youplus.core.base.BaseDeleteEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 牙科
 */
@Entity
@Table(name = "pt_dentistry")
public class PtDentistry extends BaseDeleteEntity {
    private String name;
    /**
     *联系人
     */
    private String contactPerson;
    private String phone;

    @Override
    public String toString() {
        return "PtDentistry{" +
                "name='" + name + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
