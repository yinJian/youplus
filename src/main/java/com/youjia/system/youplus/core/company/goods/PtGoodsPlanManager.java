package com.youjia.system.youplus.core.company.goods;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品计划
 *
 * @author wuweifeng wrote on 2018/11/16.
 */
@Service
public class PtGoodsPlanManager {
    @Resource
    private PtGoodsPlanRepository ptGoodsPlanRepository;

    public List<PtGoodsPlan> findAll() {
        return ptGoodsPlanRepository.findByDeleteFlagFalse();
    }

    public PtGoodsPlan add(PtGoodsPlan ptGoodsPlan) {
        return save(ptGoodsPlan);
    }

    public PtGoodsPlan update(PtGoodsPlan ptGoodsPlan) {
        return save(ptGoodsPlan);
    }

    private PtGoodsPlan save(PtGoodsPlan ptGoodsPlan) {
        return ptGoodsPlanRepository.save(ptGoodsPlan);
    }
}
