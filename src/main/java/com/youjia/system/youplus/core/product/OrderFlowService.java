package com.youjia.system.youplus.core.product;

import com.youjia.system.youplus.core.product.flow.PtOrderFlow;
import com.youjia.system.youplus.core.product.flow.PtOrderFlowManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/12/19.
 */
@Component
public class OrderFlowService {
    @Resource
    private PtOrderFlowManager ptOrderFlowManager;

    public PtOrderFlow addOrUpdate(PtOrderFlow ptOrderFlow) {
        Long id = ptOrderFlow.getId();
        if (id == null || !ptOrderFlowManager.exist(id)) {
            return ptOrderFlowManager.add(ptOrderFlow);
        } else {
            return ptOrderFlowManager.update(ptOrderFlow);
        }
    }
}
