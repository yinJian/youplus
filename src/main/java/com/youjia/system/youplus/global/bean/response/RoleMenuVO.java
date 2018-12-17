package com.youjia.system.youplus.global.bean.response;


import com.youjia.system.youplus.core.user.role.PtRole;

import java.util.Set;

/**
 * @author wuweifeng wrote on 2018/11/30.
 */
public class RoleMenuVO {
    private PtRole role;

    private Set<Long> menus;

    @Override
    public String toString() {
        return "RoleMenuVO{" +
                "role=" + role +
                ", menus=" + menus +
                '}';
    }

    public PtRole getRole() {
        return role;
    }

    public void setRole(PtRole role) {
        this.role = role;
    }

    public Set<Long> getMenus() {
        return menus;
    }

    public void setMenus(Set<Long> menus) {
        this.menus = menus;
    }
}