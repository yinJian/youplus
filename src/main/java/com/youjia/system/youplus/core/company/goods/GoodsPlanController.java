package com.youjia.system.youplus.core.company.goods;

import com.youjia.system.youplus.global.bean.BaseData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public BaseData list() {
        return null;
    }
}
