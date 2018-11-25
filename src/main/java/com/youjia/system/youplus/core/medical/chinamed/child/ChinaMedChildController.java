package com.youjia.system.youplus.core.medical.chinamed.child;

import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import com.youjia.system.youplus.global.bean.request.ChinaMedChildListQueryModel;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 分院管理
 * @author wuweifeng wrote on 2018/11/12.
 */
@RestController
@RequestMapping("chinaMedChild")
public class ChinaMedChildController {
    @Resource
    private ChinaMedChildService chinaMedChildService;

    @GetMapping("")
    public BaseData list(ChinaMedChildListQueryModel chinaMedChildListQueryModel) {
        return ResultGenerator.genSuccessResult(chinaMedChildService.find(chinaMedChildListQueryModel));
    }

    @GetMapping("/{id}")
    public BaseData one(@PathVariable Long id) {
        return ResultGenerator.genSuccessResult(chinaMedChildService.find(id));
    }

    @PostMapping("")
    public BaseData add(PtChinaMedChild ptChinaMedChild) {
        return ResultGenerator.genSuccessResult(chinaMedChildService.add(ptChinaMedChild));
    }

    @PutMapping("")
    public BaseData update(PtChinaMedChild ptChinaMedChild) {
        return ResultGenerator.genSuccessResult(chinaMedChildService.update(ptChinaMedChild));
    }

    @DeleteMapping("/{id}")
    public BaseData delete(@PathVariable Long id) {
        chinaMedChildService.delete(id);
        return ResultGenerator.genSuccessResult("删除成功");
    }
}
