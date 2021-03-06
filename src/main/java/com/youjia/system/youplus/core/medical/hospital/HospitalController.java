package com.youjia.system.youplus.core.medical.hospital;

import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import com.youjia.system.youplus.global.bean.request.HospitalListQueryModel;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
@RestController
@RequestMapping("hospital")
public class HospitalController {
    @Resource
    private HospitalService hospitalService;

    @GetMapping("")
    public BaseData list(HospitalListQueryModel hospitalListQueryModel) {
        return ResultGenerator.genSuccessResult(hospitalService.find(hospitalListQueryModel));
    }

    @GetMapping("/{id}")
    public BaseData one(@PathVariable Long id) {
        return ResultGenerator.genSuccessResult(hospitalService.find(id));
    }


    @PostMapping("")
    public BaseData add(PtHospital ptHospital) {
        return hospitalService.add(ptHospital);
    }

    @PutMapping("")
    public BaseData update(PtHospital ptHospital) {
        return ResultGenerator.genSuccessResult(hospitalService.update(ptHospital));
    }

    @DeleteMapping("/{id}")
    public BaseData delete(@PathVariable Long id) {
        hospitalService.delete(id);
        return ResultGenerator.genSuccessResult("删除成功");
    }
}
