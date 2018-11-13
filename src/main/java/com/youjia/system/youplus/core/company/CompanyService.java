package com.youjia.system.youplus.core.company;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/11/13.
 */
@Component
public class CompanyService {
    @Resource
    private CompanyManager companyManager;

    public void deal() {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName("c1");
        companyManager.addCompany(companyEntity);
    }

    public void deal1() {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName("c1");
        companyManager.addCompany1(companyEntity);
    }
}
