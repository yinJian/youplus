package com.youjia.system.youplus.core.medical.doctor;

import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import com.youjia.system.youplus.global.bean.request.DoctorListQueryModel;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
@RestController
@RequestMapping("doctor")
public class DoctorController {
    @Resource
    private DoctorService doctorService;

    @GetMapping("")
    public BaseData list(DoctorListQueryModel doctorListQueryModel) {
        return ResultGenerator.genSuccessResult(doctorService.find(doctorListQueryModel));
    }

    @GetMapping("/{id}")
    public BaseData one(@PathVariable Long id) {
        return ResultGenerator.genSuccessResult(doctorService.find(id));
    }


    @PostMapping("")
    public BaseData add(PtDoctor ptDoctor) {
        return ResultGenerator.genSuccessResult(doctorService.add(ptDoctor));
    }

    @PutMapping("")
    public BaseData update(PtDoctor ptDoctor) {
        return ResultGenerator.genSuccessResult(doctorService.update(ptDoctor));
    }

    @DeleteMapping("/{id}")
    public BaseData delete(@PathVariable Long id) {
        doctorService.delete(id);
        return ResultGenerator.genSuccessResult("删除成功");
    }
}
