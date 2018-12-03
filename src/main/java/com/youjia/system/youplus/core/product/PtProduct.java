package com.youjia.system.youplus.core.product;

import com.youjia.system.youplus.core.base.BaseDeleteEntity;

import javax.persistence.Entity;

/**
 * 产品管理（押金垫付、xxx）
 *
 * @author wuweifeng wrote on 2018/11/16.
 */
@Entity
public class PtProduct extends BaseDeleteEntity {
    /**
     * 名称
     */
    private String name;

    @Override
    public String toString() {
        return "PtProduct{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
