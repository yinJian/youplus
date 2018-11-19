package com.youjia.system.youplus.core.company.goods;

import com.youjia.system.youplus.core.base.BaseDeleteEntity;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * 商品计划
 * @author wuweifeng wrote on 2018/11/16.
 */
@Entity
@Table(name = "pt_goods_plan")
public class PtGoodsPlan extends BaseDeleteEntity {
    /**
     * 计划名字
     */
    private String name;
    /**
     * 计划内的商品
     */
    @ElementCollection
    @CollectionTable
    private List<String> goods;
    /**
     * 属于哪个企业
     */
    private Long companyId;

    @Override
    public String toString() {
        return "PtGoodsPlan{" +
                "name='" + name + '\'' +
                ", goods=" + goods +
                ", companyId=" + companyId +
                '}';
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGoods() {
        return goods;
    }

    public void setGoods(List<String> goods) {
        this.goods = goods;
    }
}
