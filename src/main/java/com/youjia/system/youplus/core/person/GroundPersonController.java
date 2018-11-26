package com.youjia.system.youplus.core.person;

import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import com.youjia.system.youplus.global.bean.request.GroundPersonListQueryModel;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 外派人员
 * @author wuweifeng wrote on 2018/11/12.
 */
@RestController
@RequestMapping("groundPerson")
public class GroundPersonController {
    @Resource
    private GroundPersonService groundPersonService;

    @GetMapping("")
    public BaseData list(GroundPersonListQueryModel groundPersonListQueryModel) {
        return ResultGenerator.genSuccessResult(groundPersonService.find(groundPersonListQueryModel));
    }

    @GetMapping("/{id}")
    public BaseData one(@PathVariable Long id) {
        return ResultGenerator.genSuccessResult(groundPersonService.find(id));
    }

    @PostMapping("")
    public BaseData add(PtGroundPerson ptGroundPerson) {
        return ResultGenerator.genSuccessResult(groundPersonService.add(ptGroundPerson));
    }

    @PutMapping("")
    public BaseData update(PtGroundPerson ptGroundPerson) {
        return ResultGenerator.genSuccessResult(groundPersonService.update(ptGroundPerson));
    }

    @DeleteMapping("/{id}")
    public BaseData delete(@PathVariable Long id) {
        groundPersonService.delete(id);
        return ResultGenerator.genSuccessResult("删除成功");
    }
}
