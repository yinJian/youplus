package com.youjia.system.youplus.core.company;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/11/13.
 */
@Service
public class CompanyManager {
    @Resource
    private CompanyRepository companyRepository;

    public void addCompany1(CompanyEntity companyEntity) {
        int i = 1 / 0;
        companyRepository.save(companyEntity);
    }

    public void addCompany(CompanyEntity companyEntity) {
        companyRepository.save(companyEntity);
    }
}
