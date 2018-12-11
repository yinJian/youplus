package com.youjia.system.youplus.core.product.order;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wuwf
 */
@Service
public class PtProductOrderManager {
    @Resource
    private PtProductOrderRepository ptProductOrderRepository;

    public PtProductOrder add(PtProductOrder ptProductOrder) {
        return save(ptProductOrder);
    }

    public PtProductOrder update(PtProductOrder ptProductOrder) {
        return save(ptProductOrder);
    }

    public PtProductOrder find(Long id) {
        return ptProductOrderRepository.getOne(id);
    }

    private PtProductOrder save(PtProductOrder ptProductOrder) {
        return ptProductOrderRepository.save(ptProductOrder);
    }
    
    public void delete(PtProductOrder ptProductOrder) {
        ptProductOrderRepository.delete(ptProductOrder);
    }

}
