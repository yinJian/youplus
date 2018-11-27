package com.youjia.system.youplus.core.dict;

import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/11/14.
 */
@RestController
@RequestMapping("dict")
public class DictController {
    @Resource
    private PtDictManager ptDictManager;

    @GetMapping("")
    public BaseData query(Integer groupId) {
        if (groupId == null) {
            return ResultGenerator.genSuccessResult(ptDictManager.findAll());
        }
        return ResultGenerator.genSuccessResult(ptDictManager.findByGroupId(groupId));
    }

    @PostMapping("")
    public BaseData add(PtDict ptDict) {
        return ResultGenerator.genSuccessResult(ptDictManager.add(ptDict));
    }

    @PutMapping("")
    public BaseData update(PtDict ptDict) {
        return ResultGenerator.genSuccessResult(ptDictManager.update(ptDict));
    }

    @DeleteMapping("/{id}")
    public BaseData delete(@PathVariable Long id) {
        ptDictManager.delete(ptDictManager.find(id));
        return ResultGenerator.genSuccessResult("删除成功");
    }
}
