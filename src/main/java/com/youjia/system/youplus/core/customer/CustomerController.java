package com.youjia.system.youplus.core.customer;

import com.youjia.system.youplus.core.order.OrderService;
import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import com.youjia.system.youplus.global.bean.request.OrderAddUpdateModel;
import com.youjia.system.youplus.global.bean.request.OrderListQueryModel;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 客户管理
 * @author wuweifeng wrote on 2018/11/16.
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Resource
    private OrderService orderService;

    @GetMapping("")
    public BaseData list(OrderListQueryModel orderListQueryModel) {
        return ResultGenerator.genSuccessResult(orderService.find(orderListQueryModel));
    }

    @GetMapping("/{id}")
    public BaseData one(@PathVariable Long id) {
        return ResultGenerator.genSuccessResult(orderService.findOne(id));
    }


    @PostMapping("")
    public BaseData add(@RequestBody OrderAddUpdateModel orderAddUpdateModel) {
        return ResultGenerator.genSuccessResult(orderService.add(orderAddUpdateModel, false));
    }

    @PutMapping("")
    public BaseData update(@RequestBody OrderAddUpdateModel orderAddUpdateModel) {
        return ResultGenerator.genSuccessResult(orderService.update(orderAddUpdateModel));
    }

    /**
     * 删除
     */
    @DeleteMapping("/{id}")
    public BaseData delete(@PathVariable Long id, String reason) {
        orderService.deleteById(id, reason);
        return ResultGenerator.genSuccessResult("操作成功，请等待审核");
    }
}
