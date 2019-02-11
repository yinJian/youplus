package com.youjia.system.youplus.core.product.flow;

import com.youjia.system.youplus.core.product.OrderFlowService;
import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping("/package")
    public BaseData packageBag(HttpServletResponse response, String files, String fileName) throws Exception {
        orderFlowService.packageBag(response, files, fileName);
        return null;
    }

    /**
     * 给微信通知用
     * 押金垫付1，2；住院押金3，4；出院结算明细5，6
     */
    @PostMapping("/stateChange")
    public BaseData notify(Long productOrderId, Integer state) {
        orderFlowService.stateChange(productOrderId, state);
        return ResultGenerator.genSuccessResult("已推送消息");
    }


}
