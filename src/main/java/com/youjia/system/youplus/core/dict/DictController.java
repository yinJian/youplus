package com.youjia.system.youplus.core.dict;

import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/11/14.
 */
@RestController
@RequestMapping("dict")
public class DictController {
    @Resource
    private PtDictManager ptDictManager;

    @RequestMapping("")
    public BaseData query() {
         return ResultGenerator.genSuccessResult(ptDictManager.findAll());
    }
}
