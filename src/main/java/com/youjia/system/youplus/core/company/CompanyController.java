package com.youjia.system.youplus.core.company;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
@RestController
@RequestMapping("company")
public class CompanyController {
    @Resource
    private CompanyService companyService;

    @RequestMapping("")
    public Object company() {
        companyService.deal();
        return "company";
    }

    @RequestMapping("error")
    public Object error() {
        companyService.deal1();
        return "company";
    }
}
