package com.youjia.system.youplus.global.bean.response;


import com.youjia.system.youplus.core.user.role.PtRole;

import java.util.List;
import java.util.Set;

/**
 * @author wuweifeng wrote on 2018/11/30.
 */
public class RoleMenuVO {
    private PtRole role;

    private List<String> menus;

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

    public List<String> getMenus() {
        return menus;
    }

    public void setMenus(List<String> menus) {
        this.menus = menus;
    }
}