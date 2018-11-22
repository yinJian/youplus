package com.youjia.system.youplus.core.company.goods;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品计划
 * @author wuweifeng wrote on 2018/11/16.
 */
@Component
@Transactional(rollbackFor = Exception.class)
public class GoodsPlanService {
    @Resource
    private PtGoodsPlanManager ptGoodsPlanManager;
    @Resource
    private GoodsService goodsService;

    public List<PtGoodsPlan> findByCompanyId(Long companyId) {
        return ptGoodsPlanManager.findByCompanyId(companyId);
    }

    public PtGoodsPlan add(PtGoodsPlan ptGoodsPlan) {
        return ptGoodsPlanManager.add(ptGoodsPlan);
    }

    /**
     * 更新plan
     */
    public PtGoodsPlan update(PtGoodsPlan ptGoodsPlan) {
        //如果是下架操作，则下架所有的该plan的商品temp
        if (ptGoodsPlan.isDeleteFlag()) {
            goodsService.deleteTempByPlanId(ptGoodsPlan.getId());
        }

        return ptGoodsPlanManager.update(ptGoodsPlan);
    }
}
