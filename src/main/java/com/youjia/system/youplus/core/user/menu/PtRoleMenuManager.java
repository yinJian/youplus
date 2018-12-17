package com.youjia.system.youplus.core.user.menu;

import com.xiaoleilu.hutool.util.CollectionUtil;
import com.youjia.system.youplus.core.user.role.PtRole;
import com.youjia.system.youplus.core.user.role.PtRoleManager;
import com.youjia.system.youplus.global.event.RoleMenuChangeEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wuweifeng wrote on 2018/1/11.
 */
@Component
public class PtRoleMenuManager {
    @Resource
    private PtRoleMenuRepository ptRoleMenuRepository;

    @Resource
    private PtRoleManager ptRoleManager;
    @Resource
    private ApplicationEventPublisher eventPublisher;


    /**
     * 给某个role赋值权限
     *
     * @param menuId
     *         menuId
     * @param roleId
     *         roleId
     * @return menuRole
     */
    public PtRoleMenu add(Long menuId, Long roleId) {
        PtRoleMenu menuRole = addOne(menuId, roleId);

        publishRoleEvent(CollectionUtil.newArrayList(roleId));
        return menuRole;
    }

    public void deleteByRoleId(Long roleId) {
        ptRoleMenuRepository.deleteByRoleId(roleId);
    }

    private PtRoleMenu addOne(Long menuId, Long roleId) {
        PtRoleMenu ptMenuRole = ptRoleMenuRepository.findFirstByMenuIdAndRoleId(menuId, roleId);
        if (ptMenuRole == null) {
            ptMenuRole = new PtRoleMenu();
            ptMenuRole.setMenuId(menuId);
            ptMenuRole.setRoleId(roleId);
            ptMenuRole = ptRoleMenuRepository.save(ptMenuRole);
        }
        return ptMenuRole;
    }

    /**
     * 查询拥有某个菜单的所有role
     *
     * @param menuId
     *         菜单id
     * @return role集合
     */
    public List<PtRole> findRolesByMenu(Long menuId) {
        List<PtRoleMenu> menuRoles = ptRoleMenuRepository.findByMenuId(menuId);
        return menuRoles.stream().map(PtRoleMenu::getRoleId).map(roleId -> ptRoleManager.find(roleId)).collect
                (Collectors
                        .toList());
    }

    private void publishRoleEvent(List<Long> roleIds) {
        //发布角色菜单事件
        eventPublisher.publishEvent(new RoleMenuChangeEvent(roleIds));
    }
}