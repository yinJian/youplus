package com.youjia.system.youplus.core.product.flow;

import com.youjia.system.youplus.core.product.OrderFlowService;
import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/12/19.
 */
@RestController
@RequestMapping("/orderFlow")
public class OrderFlowController {
    @Resource
    private OrderFlowService orderFlowService;


    @PutMapping("")
    public BaseData modify(PtOrderFlow ptOrderFlow) {
        return ResultGenerator.genSuccessResult(orderFlowService.addOrUpdate(ptOrderFlow));
    }
}
