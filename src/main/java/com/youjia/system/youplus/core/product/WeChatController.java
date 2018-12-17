package com.youjia.system.youplus.core.product;

import com.youjia.system.youplus.core.person.GroundPersonService;
import com.youjia.system.youplus.core.product.order.ProductOrderService;
import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import com.youjia.system.youplus.global.bean.request.ProductOrderListQueryModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 微信H5
 * @author wuweifeng wrote on 2018/11/12.
 */
@RestController
@RequestMapping("weChat")
public class WeChatController {
    @Resource
    private GroundPersonService groundPersonService;
    @Resource
    private ProductOrderService productOrderService;

    @GetMapping("")
    public BaseData login(String mobile) {
        return ResultGenerator.genSuccessResult(groundPersonService.findByMobile(mobile));
    }

    @GetMapping("/orders")
    public BaseData orders(Boolean finish) {
        ProductOrderListQueryModel productOrderListQueryModel = new ProductOrderListQueryModel();
        if(finish == null) {
            finish = false;
        }
        if (finish) {
            productOrderListQueryModel.setState(8);
        } else {
            productOrderListQueryModel.setNotState(8);
        }
        return ResultGenerator.genSuccessResult(productOrderService.findAll(productOrderListQueryModel));
    }

    @GetMapping("/orders/{id}")
    public BaseData orderOne(@PathVariable Long id) {
        return ResultGenerator.genSuccessResult(productOrderService.find(id));
    }
}
