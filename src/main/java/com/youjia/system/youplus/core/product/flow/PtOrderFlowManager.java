package com.youjia.system.youplus.core.product.flow;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wuwf
 */
@Service
public class PtOrderFlowManager {
    @Resource
    private PtOrderFlowRepository ptOrderFlowRepository;

    public PtOrderFlow findByProductOrderId(Long productOrderId) {
        return ptOrderFlowRepository.findFirstByProductOrderId(productOrderId);
    }

    public PtOrderFlow add(PtOrderFlow ptOrderFlow) {
        return save(ptOrderFlow);
    }

    public PtOrderFlow update(PtOrderFlow ptOrderFlow) {
        return save(ptOrderFlow);
    }

    public PtOrderFlow find(Long id) {
        return ptOrderFlowRepository.getOne(id);
    }

    private PtOrderFlow save(PtOrderFlow ptOrderFlow) {
        return ptOrderFlowRepository.save(ptOrderFlow);
    }
    
    public void delete(PtOrderFlow ptOrderFlow) {
        ptOrderFlowRepository.delete(ptOrderFlow);
    }

}
