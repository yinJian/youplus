package com.youjia.system.youplus.global.bean.request;

import java.util.ArrayList;
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
    private Integer state = 0;

    private List<Long> menuIds = new ArrayList<>();

    @Override
    public String toString() {
        return "RoleAddRequestModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", menuIds=" + menuIds +
                '}';
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }
}
