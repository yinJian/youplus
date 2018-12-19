package com.youjia.system.youplus.core.medical.physical;

import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import com.youjia.system.youplus.global.bean.request.PhysicalListQueryModel;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 分院管理
 * @author wuweifeng wrote on 2018/11/12.
 */
@RestController
@RequestMapping("physical")
public class PhysicalController {
    @Resource
    private PhysicalService physicalService;

    @GetMapping("")
    public BaseData list(PhysicalListQueryModel physicalListQueryModel) {
        return ResultGenerator.genSuccessResult(physicalService.find(physicalListQueryModel));
    }

    @GetMapping("/{id}")
    public BaseData one(@PathVariable Long id) {
        return ResultGenerator.genSuccessResult(physicalService.find(id));
    }

    @PostMapping("")
    public BaseData add(@Valid PtPhysical ptPhysical, BindingResult bindingResult) {
        return ResultGenerator.genSuccessResult(physicalService.add(ptPhysical));
    }

    @PutMapping("")
    public BaseData update(@Valid PtPhysical ptPhysical, BindingResult bindingResult) {
        return ResultGenerator.genSuccessResult(physicalService.update(ptPhysical));
    }

    @DeleteMapping("/{id}")
    public BaseData delete(@PathVariable Long id) {
        physicalService.delete(id);
        return ResultGenerator.genSuccessResult("删除成功");
    }
}
