package com.youjia.system.youplus.core.order;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/11/22.
 */
@Service
public class PtOrderRelationManager {
    @Resource
    private PtOrderRelationRepository ptOrderRelationRepository;

    public PtOrderRelation add(PtOrderRelation ptOrderRelation) {
        return save(ptOrderRelation);
    }

    public PtOrderRelation update(PtOrderRelation ptOrderRelation) {
        return save(ptOrderRelation);
    }

    private PtOrderRelation save(PtOrderRelation ptOrderRelation) {
        return ptOrderRelationRepository.save(ptOrderRelation);
    }
}
