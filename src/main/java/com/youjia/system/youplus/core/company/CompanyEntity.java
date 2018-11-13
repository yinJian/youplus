package com.youjia.system.youplus.core.company;

import com.youjia.system.youplus.core.base.BaseEntity;

import javax.persistence.Entity;

/**
 * 公司
 * @author wuweifeng wrote on 2018/11/1.
 */
@Entity
public class CompanyEntity extends BaseEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
