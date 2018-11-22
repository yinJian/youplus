package com.youjia.system.youplus.core.company.goods;

import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/11/16.
 */
@RestController
@RequestMapping("/goodsPlan")
public class GoodsPlanController {
    @Resource
    private GoodsPlanService goodsPlanService;

    @GetMapping("")
    public BaseData list(Long companyId) {
        return ResultGenerator.genSuccessResult(goodsPlanService.findByCompanyId(companyId));
    }

    @PostMapping("")
    public BaseData add(PtGoodsPlan ptGoodsPlan) {
        return ResultGenerator.genSuccessResult(goodsPlanService.add(ptGoodsPlan));
    }

    @PutMapping("")
    public BaseData update(PtGoodsPlan ptGoodsPlan) {
        return ResultGenerator.genSuccessResult(goodsPlanService.update(ptGoodsPlan));
    }
}
