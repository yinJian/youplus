package com.youjia.system.youplus.core.product;

import com.youjia.system.youplus.core.person.PtGroundPerson;
import com.youjia.system.youplus.core.person.PtGroundPersonManager;
import com.youjia.system.youplus.core.product.flow.PtOrderFlow;
import com.youjia.system.youplus.core.product.flow.PtOrderFlowManager;
import com.youjia.system.youplus.core.product.order.PtProductOrder;
import com.youjia.system.youplus.core.product.order.PtProductOrderManager;
import com.youjia.system.youplus.core.wechat.ChangeStateEvent;
import com.youjia.system.youplus.core.wechat.event.ChangeStateBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/12/19.
 */
@Component
public class OrderFlowService {
    @Resource
    private PtOrderFlowManager ptOrderFlowManager;
    @Resource
    private PtProductOrderManager ptProductOrderManager;
    @Resource
    private PtGroundPersonManager ptGroundPersonManager;
    @Resource
    private ApplicationEventPublisher applicationEventPublisher;


    public PtOrderFlow addOrUpdate(PtOrderFlow ptOrderFlow) {
        Long id = ptOrderFlow.getId();
        if (id == null || !ptOrderFlowManager.exist(id)) {
            return ptOrderFlowManager.add(ptOrderFlow);
        } else {
            return ptOrderFlowManager.update(ptOrderFlow);
        }
    }

    public void stateChange(Long productOrderId, Integer stateChange) {
        PtProductOrder ptProductOrder = ptProductOrderManager.find(productOrderId);

        PtGroundPerson ptGroundPerson = ptGroundPersonManager.find(ptProductOrder.getGroundPersonId());

        String statesMsg = "审核通过";
        if (stateChange / 2 == 0) {
            statesMsg = "审核失败";
        }
        String orderStatus = "";
        if (stateChange <= 2) {
            orderStatus = "提交押金垫付文件审核";
        } else if (stateChange <= 4) {
            orderStatus = "上传住院押金条审核";
        } else if (stateChange <= 6) {
            orderStatus = "出院结算明细审核";
        }
        applicationEventPublisher.publishEvent(new ChangeStateEvent(new ChangeStateBean(ptGroundPerson.getOpenid(),
                statesMsg, orderStatus)));
    }
}
