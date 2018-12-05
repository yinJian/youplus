package com.youjia.system.youplus.core.user.role;

import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import com.youjia.system.youplus.global.bean.request.RoleAddRequestModel;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/1/11.
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    /**
     * 获取所有角色
     */
    @GetMapping("")
    public BaseData get() {
        return ResultGenerator.genSuccessResult(roleService.findAll());
    }

    @GetMapping("/{id}")
    public BaseData one(@PathVariable Long id) {
        return ResultGenerator.genSuccessResult(roleService.findOne(id));
    }

    /**
     * 添加角色
     *
     * @param roleAddRequestModel
     *         名字
     * @return 结果
     */
    @PostMapping("")
    public BaseData add(@RequestBody RoleAddRequestModel roleAddRequestModel) {
        return ResultGenerator.genSuccessResult(roleService.add(roleAddRequestModel));
    }

    /**
     * 修改角色
     *
     * @param roleAddRequestModel
     *         名字
     * @return 结果
     */
    @PutMapping("")
    public BaseData update(@RequestBody RoleAddRequestModel roleAddRequestModel) {
        return ResultGenerator.genSuccessResult(roleService.update(roleAddRequestModel));
    }

    /**
     * 删除角色
     *
     * @return 结果
     */
    @DeleteMapping("/{id}")
    public BaseData delete(@PathVariable Long id) {
        boolean success = roleService.delete(id);
        if (success) {
            return ResultGenerator.genSuccessResult("删除成功");
        }
        return ResultGenerator.genFailResult("请先删除所有该角色的用户对应关系");
    }
}
