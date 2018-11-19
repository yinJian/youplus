package com.youjia.system.youplus.core.company.goods;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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

    /**
     * 提交更新时，修改原来的状态为"待审核"，将新的属性全部存到temp中
     *
     * @param ptGoods
     *         ptGoods
     * @return ptGoods
     */
    public PtGoods update(PtGoods ptGoods) {
        return save(ptGoods);
    }

    public PtGoodsTemp updateTemp(PtGoodsTemp ptGoodsTemp) {
        return saveTemp(ptGoodsTemp);
    }

    public PtGoodsTemp save(PtGoodsTemp ptGoodsTemp) {
        return ptGoodsTempRepository.save(ptGoodsTemp);
    }

    public PtGoodsTemp findTempByCompanyId(Long companyId) {
        return ptGoodsTempRepository.findFirstByGoodsIdOrderByIdDesc(companyId);
    }

    public PtGoodsTemp findOneTemp(Long id) {
        return ptGoodsTempRepository.getOne(id);
    }

    private PtGoods save(PtGoods ptGoods) {
        return ptGoodsRepository.save(ptGoods);
    }

    private PtGoodsTemp saveTemp(PtGoodsTemp ptGoods) {
        return ptGoodsTempRepository.save(ptGoods);
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

    public Page<PtGoodsTemp> findAllTemp(Specification<PtGoodsTemp> var1, Pageable var2) {
        return ptGoodsTempRepository.findAll(var1, var2);
    }
}
