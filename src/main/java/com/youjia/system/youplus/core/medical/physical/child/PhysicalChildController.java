package com.youjia.system.youplus.core.medical.physical.child;

import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import com.youjia.system.youplus.global.bean.request.PhysicalChildListQueryModel;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 分院管理
 * @author wuweifeng wrote on 2018/11/12.
 */
@RestController
@RequestMapping("physicalChild")
public class PhysicalChildController {
    @Resource
    private PhysicalChildService physicalChildService;

    @GetMapping("")
    public BaseData list(PhysicalChildListQueryModel physicalChildListQueryModel) {
        return ResultGenerator.genSuccessResult(physicalChildService.find(physicalChildListQueryModel));
    }

    @GetMapping("/{id}")
    public BaseData one(@PathVariable Long id) {
        return ResultGenerator.genSuccessResult(physicalChildService.find(id));
    }

    @PostMapping("")
    public BaseData add(@Valid PtPhysicalChild ptPhysicalChild, BindingResult bindingResult) {
        return ResultGenerator.genSuccessResult(physicalChildService.add(ptPhysicalChild));
    }

    @PutMapping("")
    public BaseData update(@Valid PtPhysicalChild ptPhysicalChild, BindingResult bindingResult) {
        return ResultGenerator.genSuccessResult(physicalChildService.update(ptPhysicalChild));
    }

    @DeleteMapping("/{id}")
    public BaseData delete(@PathVariable Long id) {
        physicalChildService.delete(id);
        return ResultGenerator.genSuccessResult("删除成功");
    }
}
