package com.youjia.system.youplus.core.user.user;

import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 外派人员
 * @author wuweifeng wrote on 2018/11/12.
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private PtUserManager ptUserManager;

    @GetMapping("")
    public BaseData list() {
        return ResultGenerator.genSuccessResult(ptUserManager.find());
    }

    //@GetMapping("/{id}")
    //public BaseData one(@PathVariable Long id) {
    //    return ResultGenerator.genSuccessResult(UserService.find(id));
    //}
    //
    //@PostMapping("")
    //public BaseData add(PtUser ptUser) {
    //    return ResultGenerator.genSuccessResult(UserService.add(ptUser));
    //}
    //
    //@PutMapping("")
    //public BaseData update(PtUser ptUser) {
    //    return ResultGenerator.genSuccessResult(UserService.update(ptUser));
    //}
    //
    //@DeleteMapping("/{id}")
    //public BaseData delete(@PathVariable Long id) {
    //    UserService.delete(id);
    //    return ResultGenerator.genSuccessResult("删除成功");
    //}
}
