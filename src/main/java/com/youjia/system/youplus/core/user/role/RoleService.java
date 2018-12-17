package com.youjia.system.youplus.core.user.role;

import com.xiaoleilu.hutool.util.BeanUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.youjia.system.youplus.core.user.menu.PtMenu;
import com.youjia.system.youplus.core.user.menu.PtMenuManager;
import com.youjia.system.youplus.core.user.menu.PtRoleMenuManager;
import com.youjia.system.youplus.core.user.userrole.PtUserRoleManager;
import com.youjia.system.youplus.global.bean.request.RoleAddRequestModel;
import com.youjia.system.youplus.global.bean.response.RoleMenuVO;
import com.youjia.system.youplus.global.event.RoleMenuChangeEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色管理
 *
 * @author wuweifeng wrote on 2018/1/18.
 */
@Component
public class RoleService {
    @Resource
    private PtRoleManager ptRoleManager;
    @Resource
    private PtUserRoleManager ptUserRoleManager;
    @Resource
    private PtRoleMenuManager ptRoleMenuManager;
    @Resource
    private PtMenuManager ptMenuManager;
    @Resource
    private ApplicationEventPublisher eventPublisher;


    public List<PtRole> findAll() {
        return ptRoleManager.findAll();
    }

    public RoleMenuVO findOne(Long id) {
        PtRole ptRole = ptRoleManager.find(id);
        List<PtMenu> ptMenus = ptMenuManager.findAllMenuByRoles(Arrays.asList(ptRole));

        RoleMenuVO roleMenuVO = new RoleMenuVO();

        roleMenuVO.setMenus(ptMenus.stream().map(PtMenu::getId).collect(Collectors.toSet()));
        roleMenuVO.setRole(ptRole);

        return roleMenuVO;
    }

    //public Set<SimpleMenu> findMenusByRoleId(Long roleId) {
    //    PtRole ptRole = ptRoleManager.find(roleId);
    //    List<PtMenu> ptMenus = ptMenuManager.findAllMenuByRoles(Arrays.asList(ptRole));
    //
    //    Set<SimpleMenu> menus = new HashSet<>();
    //    for (PtMenu ptMenu : ptMenus) {
    //        if (ptMenu.getParentId() == 0) {
    //            SimpleMenu simpleMenu = new SimpleMenu();
    //            simpleMenu.setId(ptMenu.getId());
    //            simpleMenu.setName(ptMenu.getName());
    //            simpleMenu.setGroupId(ptMenu.getGroupId());
    //            simpleMenu.setOrderNum(ptMenu.getOrderNum());
    //            List<SimpleMenu> childMenus = new ArrayList<>();
    //            simpleMenu.setChildMenus(childMenus);
    //            menus.add(simpleMenu);
    //        }
    //    }
    //    for (PtMenu ptMenu : ptMenus) {
    //        Long parentId = ptMenu.getParentId();
    //        if (ptMenu.getParentId() == 0) {
    //            continue;
    //        }
    //        for (SimpleMenu menu : menus) {
    //            if (parentId.equals(menu.getGroupId())) {
    //                SimpleMenu simpleMenu = new SimpleMenu();
    //                simpleMenu.setName(ptMenu.getName());
    //                simpleMenu.setId(ptMenu.getId());
    //                simpleMenu.setGroupId(ptMenu.getGroupId());
    //                simpleMenu.setOrderNum(ptMenu.getOrderNum());
    //                menu.getChildMenus().add(simpleMenu);
    //            }
    //        }
    //    }
    //
    //    return menus;
    //}

    /**
     * 新增一个role。只有超级管理员和公司管理员才有该权限
     *
     * @param roleAddRequestModel
     *         roleAddRequestModel
     * @return PtRole
     */
    public PtRole add(RoleAddRequestModel roleAddRequestModel) {
        PtRole ptRole = new PtRole();
        BeanUtil.copyProperties(roleAddRequestModel, ptRole);
        ptRole = ptRoleManager.add(ptRole);

        List<Long> menuIds = roleAddRequestModel.getMenuIds();
        if (CollectionUtil.isNotEmpty(menuIds)) {
            for (Long menuId : menuIds) {
                ptRoleMenuManager.add(menuId, ptRole.getId());
            }
        }

        return ptRole;
    }

    public PtRole update(RoleAddRequestModel roleAddRequestModel) {
        PtRole ptRole = ptRoleManager.find(roleAddRequestModel.getId());
        BeanUtil.copyProperties(roleAddRequestModel, ptRole, BeanUtil.CopyOptions.create().setIgnoreNullValue(true));

        List<Long> menuIds = roleAddRequestModel.getMenuIds();
        if (CollectionUtil.isNotEmpty(menuIds)) {
            ptRoleMenuManager.deleteByRoleId(roleAddRequestModel.getId());
            for (Long menuId : menuIds) {
                ptRoleMenuManager.add(menuId, ptRole.getId());
            }
            eventPublisher.publishEvent(new RoleMenuChangeEvent(CollectionUtil.newArrayList(ptRole.getId())));
        }

        return ptRoleManager.update(ptRole);
    }

    public boolean delete(Long id) {
        //如果公司内还有人是该角色，就不让删除
        if (ptUserRoleManager.findByRoleId(id).size() > 0) {
            return false;
        }
        ptRoleManager.delete(ptRoleManager.find(id));
        //发布菜单事件
        eventPublisher.publishEvent(new RoleMenuChangeEvent(CollectionUtil.newArrayList(id)));
        return true;
    }

}