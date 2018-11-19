package com.youjia.system.youplus.core.company.goods;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuweifeng wrote on 2018/11/16.
 */
@Component
public class GoodsPlanService {
    @Resource
    private PtGoodsPlanManager ptGoodsPlanManager;

    public List<PtGoodsPlan> findAll() {
        return ptGoodsPlanManager.findAll();
    }

    public PtGoodsPlan add(PtGoodsPlan ptGoodsPlan) {
        return ptGoodsPlanManager.add(ptGoodsPlan);
    }

    public PtGoodsPlan update(PtGoodsPlan ptGoodsPlan) {
        return ptGoodsPlanManager.update(ptGoodsPlan);
    }
}
