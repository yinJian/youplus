package com.youjia.system.youplus.core.dict.area;

import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/11/15.
 */
@RestController
@RequestMapping("/area")
public class AreaController {
    @Resource
    private AreaManager areaManager;

    @GetMapping
    public BaseData get(Long parentId) {
        return ResultGenerator.genSuccessResult(areaManager.findByParentId(parentId));
    }

    @GetMapping("list")
    public BaseData list(Long parentId) {
        return ResultGenerator.genSuccessResult(areaManager.findByParentId(parentId));
    }

    @PostMapping
    public BaseData add(AreaEntity areaEntity) {
        return ResultGenerator.genSuccessResult(areaManager.add(areaEntity));
    }

    @PutMapping
    public BaseData update(AreaEntity areaEntity) {
        return ResultGenerator.genSuccessResult(areaManager.update(areaEntity));
    }

    @DeleteMapping("/{id}")
    public BaseData delete(@PathVariable Long id) {
        areaManager.delete(id);
        return ResultGenerator.genSuccessResult("删除成功");
    }
}
