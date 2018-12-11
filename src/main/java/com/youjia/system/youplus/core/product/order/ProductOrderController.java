package com.youjia.system.youplus.core.product.order;

import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import com.youjia.system.youplus.global.bean.request.ProductOrderAddModel;
import com.youjia.system.youplus.global.bean.request.ProductOrderListQueryModel;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 产品管理
 * @author wuweifeng wrote on 2018/11/12.
 */
@RestController
@RequestMapping("productOrder")
public class ProductOrderController {
    @Resource
    private ProductOrderService productOrderService;

    @GetMapping("")
    public BaseData list(ProductOrderListQueryModel productOrderListQueryModel) {
        return ResultGenerator.genSuccessResult(productOrderService.findAll(productOrderListQueryModel));
    }

    @GetMapping("/{id}")
    public BaseData one(@PathVariable Long id) {
        return ResultGenerator.genSuccessResult(productOrderService.find(id));
    }

    @PostMapping("")
    public BaseData add(@RequestBody ProductOrderAddModel ptProduct) {
        return ResultGenerator.genSuccessResult(productOrderService.add(ptProduct));
    }

    @PutMapping("")
    public BaseData update(@RequestBody ProductOrderAddModel ptProduct) {
        return ResultGenerator.genSuccessResult(productOrderService.update(ptProduct));
    }

    @DeleteMapping("/{id}")
    public BaseData delete(@PathVariable Long id) {
        productOrderService.delete(id);
        return ResultGenerator.genSuccessResult("删除成功");
    }
}
