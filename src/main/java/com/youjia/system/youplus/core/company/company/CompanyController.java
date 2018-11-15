package com.youjia.system.youplus.core.company.company;

import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import com.youjia.system.youplus.global.bean.request.CompanyListQueryModel;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
@RestController
@RequestMapping("company")
public class CompanyController {
    @Resource
    private CompanyService companyService;


    @GetMapping("")
    public BaseData list(CompanyListQueryModel companyListQueryModel) {
        return ResultGenerator.genSuccessResult(companyService.find(companyListQueryModel));
    }

    @GetMapping("/{id}")
    public BaseData one(@PathVariable Long id) {
        return ResultGenerator.genSuccessResult(companyService.findOne(id));
    }

    @PostMapping("")
    public BaseData add(PtCompany ptCompany) {
        return ResultGenerator.genSuccessResult(companyService.add(ptCompany));
    }

    @PutMapping("")
    public BaseData update(PtCompany ptCompany, String reason) {
        return ResultGenerator.genSuccessResult(companyService.update(ptCompany, reason));
    }
}
