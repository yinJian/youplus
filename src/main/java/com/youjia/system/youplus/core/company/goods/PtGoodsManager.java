package com.youjia.system.youplus.core.company.goods;

import com.youjia.system.youplus.global.util.Constant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuweifeng wrote on 2018/11/16.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PtGoodsManager {
    @Resource
    private PtGoodsRepository ptGoodsRepository;
    @Resource
    private PtGoodsTempRepository ptGoodsTempRepository;

    public PtGoods add(PtGoods ptGoods) {
        return save(ptGoods);
    }

    public PtGoodsTemp addTemp(PtGoodsTemp ptGoods) {
        return ptGoodsTempRepository.save(ptGoods);
    }

    public PtGoodsTemp updateTemp(PtGoodsTemp ptGoodsTemp) {
        return save(ptGoodsTemp);
    }

    public PtGoods update(PtGoods ptGoods) {
        return save(ptGoods);
    }

    private PtGoods save(PtGoods ptGoods) {
        return ptGoodsRepository.save(ptGoods);
    }

    private PtGoodsTemp save(PtGoodsTemp ptGoodsTemp) {
        return ptGoodsTempRepository.save(ptGoodsTemp);
    }

    /**
     * 商品下架
     */
    public void delete(PtGoods ptGoods) {
        ptGoods.setDeleteFlag(true);
        save(ptGoods);
    }

    /**
     * 下架商品
     */
    public void deleteTemp(PtGoodsTemp ptGoodsTemp) {
        ptGoodsTemp.setDeleteFlag(true);
        ptGoodsTemp.setReason(Constant.REASON_DELETE);
        ptGoodsTemp.setOperatorType(Constant.REASON_DELETE);
        save(ptGoodsTemp);
    }

    /**
     * 根据商品计划查询所有temp
     */
    public List<PtGoodsTemp> findByPlanId(Long planId) {
        return ptGoodsTempRepository.findByPtGoodsPlanId(planId);
    }

    public PtGoodsTemp findOneTemp(Long id) {
        return ptGoodsTempRepository.getOne(id);
    }

    public PtGoodsTemp findOneTempByGoodsId(Long goodsId) {
        return ptGoodsTempRepository.findFirstByGoodsIdOrderByIdDesc(goodsId);
    }

    public PtGoods findOne(Long id) {
        return ptGoodsRepository.getOne(id);
    }


    /**
     * 分页查找
     *
     * @param var1
     *         var1
     * @param var2
     *         var2
     * @return Page
     */
    public Page<PtGoods> findAll(Specification<PtGoods> var1, Pageable var2) {
        return ptGoodsRepository.findAll(var1, var2);
    }

    /**
     * 根据公司id查询
     */
    public Page<PtGoods> findByCompanyId(Long companyId, Pageable var2) {
        return ptGoodsRepository.findByCompanyIdAndDeleteFlagFalse(companyId, var2);
    }

    public Page<PtGoodsTemp> findAllTemp(Specification<PtGoodsTemp> var1, Pageable var2) {
        return ptGoodsTempRepository.findAll(var1, var2);
    }
}
