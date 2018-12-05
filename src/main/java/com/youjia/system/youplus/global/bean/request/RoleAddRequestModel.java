package com.youjia.system.youplus.global.bean.request;

import java.util.List;

/**
 * @author wuweifeng wrote on 2018/11/26.
 */
public class RoleAddRequestModel {
    private Long id;
    /**
     * 角色名（admin，level1，level2，level3）
     */
    private String name;
    /**
     * 角色描述（超级管理员，1级客户，2级客户，3级客户）
     */
    private String sign;

    private List<Long> menuIds;

    @Override
    public String toString() {
        return "RoleAddRequestModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sign='" + sign + '\'' +
                ", menuIds=" + menuIds +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }
}
