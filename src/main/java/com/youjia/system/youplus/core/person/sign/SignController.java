package com.youjia.system.youplus.core.person.sign;

import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 外派人员查询签约
 * @author wuweifeng wrote on 2018/11/12.
 */
@RestController
@RequestMapping("sign")
public class SignController {
    @Resource
    private PtSignManager ptSignManager;

    @GetMapping("")
    public BaseData list(Long groundPersonId) {
        return ResultGenerator.genSuccessResult(ptSignManager.findByGroundPersonId(groundPersonId));
    }

    @GetMapping("/{id}")
    public BaseData one(@PathVariable Long id) {
        return ResultGenerator.genSuccessResult(ptSignManager.find(id));
    }

    @PostMapping("")
    public BaseData add(PtSign ptSign) {
        return ResultGenerator.genSuccessResult(ptSignManager.add(ptSign));
    }

    @PutMapping("")
    public BaseData update(PtSign ptSign) {
        return ResultGenerator.genSuccessResult(ptSignManager.update(ptSign));
    }

    @DeleteMapping("/{id}")
    public BaseData delete(@PathVariable Long id) {
        ptSignManager.delete(ptSignManager.find(id));
        return ResultGenerator.genSuccessResult("删除成功");
    }
}
