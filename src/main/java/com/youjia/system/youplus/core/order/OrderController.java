package com.youjia.system.youplus.core.order;

import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import com.youjia.system.youplus.global.bean.request.OrderAddUpdateModel;
import com.youjia.system.youplus.global.bean.request.OrderListQueryModel;
import com.youjia.system.youplus.global.bean.request.OrderTempListQueryModel;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/11/16.
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("")
    public BaseData list(OrderListQueryModel orderListQueryModel) {
        return ResultGenerator.genSuccessResult(orderService.find(orderListQueryModel));
    }

    @GetMapping("/tempList")
    public BaseData confirm(OrderTempListQueryModel orderTempListQueryModel) {
        return ResultGenerator.genSuccessResult(orderService.findTemp(orderTempListQueryModel));
    }

    @GetMapping("/{id}")
    public BaseData one(@PathVariable Long id) {
        return ResultGenerator.genSuccessResult(orderService.findOne(id));
    }

    /**
     * 包含被修改的详情
     */
    @GetMapping("/{id}/detail")
    public BaseData detail(@PathVariable Long id) {
        return ResultGenerator.genSuccessResult(orderService.findDetail(id));
    }

    @PostMapping("/{id}/detail/confirm")
    public BaseData confirm(@PathVariable Long id, Boolean confirm) {
        orderService.confirm(id, confirm);
        return ResultGenerator.genSuccessResult("操作成功");
    }

    @PostMapping("")
    public BaseData add(@RequestBody OrderAddUpdateModel orderAddUpdateModel) {
        return ResultGenerator.genSuccessResult(orderService.add(orderAddUpdateModel, true));
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
