package com.youjia.system.youplus.core.product.flow;

import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 订单流程设置
 * @author wuweifeng wrote on 2018/11/12.
 */
@RestController
@RequestMapping("orderFlow")
public class OrderFlowSettingController {
    @Resource
    private PtOrderFlowSettingManager ptOrderFlowSettingManager;

    @GetMapping("")
    public BaseData list(Long productId) {
        return ResultGenerator.genSuccessResult(ptOrderFlowSettingManager.findByProductId(productId));
    }

    @PostMapping("")
    public BaseData add(PtOrderFlowSetting ptOrderFlowSetting) {
        return ResultGenerator.genSuccessResult(ptOrderFlowSettingManager.add(ptOrderFlowSetting));
    }

    @PutMapping("")
    public BaseData update(PtOrderFlowSetting ptOrderFlowSetting) {
        return ResultGenerator.genSuccessResult(ptOrderFlowSettingManager.update(ptOrderFlowSetting));
    }

    @DeleteMapping("/{id}")
    public BaseData delete(@PathVariable Long id) {
        ptOrderFlowSettingManager.delete(ptOrderFlowSettingManager.find(id));
        return ResultGenerator.genSuccessResult("删除成功");
    }
}
