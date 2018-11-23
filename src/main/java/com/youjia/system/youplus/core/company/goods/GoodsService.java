package com.youjia.system.youplus.core.company.goods;

import com.xiaoleilu.hutool.util.BeanUtil;
import com.youjia.system.youplus.core.company.youserver.PtCashPrePay;
import com.youjia.system.youplus.core.company.youserver.PtCashPrePayManager;
import com.youjia.system.youplus.core.company.youserver.PtPhoneDoctor;
import com.youjia.system.youplus.core.company.youserver.PtPhoneDoctorManager;
import com.youjia.system.youplus.core.user.user.PtUserManager;
import com.youjia.system.youplus.global.bean.SimplePage;
import com.youjia.system.youplus.global.bean.request.GoodsAddUpdateModel;
import com.youjia.system.youplus.global.bean.request.GoodsTempListQueryModel;
import com.youjia.system.youplus.global.bean.response.GoodsListVO;
import com.youjia.system.youplus.global.bean.response.GoodsModifyDetailVO;
import com.youjia.system.youplus.global.bean.response.GoodsTempListVO;
import com.youjia.system.youplus.global.specify.Criteria;
import com.youjia.system.youplus.global.specify.Restrictions;
import com.youjia.system.youplus.global.util.Constant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品管理
 *
 * @author wuweifeng wrote on 2018/11/20.
 */
@Component
public class GoodsService {
    @Resource
    private PtGoodsManager ptGoodsManager;
    @Resource
    private PtGoodsPlanManager ptGoodsPlanManager;
    @Resource
    private PtPhoneDoctorManager ptPhoneDoctorManager;
    @Resource
    private PtCashPrePayManager ptCashPrePayManager;
    @Resource
    private PtUserManager ptUserManager;

    public PtGoods add(GoodsAddUpdateModel goodsAddMode) {
        PtGoods ptGoods = new PtGoods();
        BeanUtil.copyProperties(goodsAddMode, ptGoods);
        ptGoods.setStatus(Constant.STATE_CONFIRM);
        //添加goods
        ptGoods = ptGoodsManager.add(ptGoods);

        PtCashPrePay cashPrePay = goodsAddMode.getPtCashPrePay();
        if (cashPrePay != null) {
            cashPrePay.setGoodsId(ptGoods.getId());
            cashPrePay = ptCashPrePayManager.add(cashPrePay);
            ptGoods.setYouCashPrePayId(cashPrePay.getId());
        } else {
            ptGoods.setYouCashPrePayId(0L);
        }

        PtPhoneDoctor ptPhoneDoctor = goodsAddMode.getPtPhoneDoctor();
        if (ptPhoneDoctor != null) {
            ptPhoneDoctor.setGoodsId(ptGoods.getId());
            ptPhoneDoctor = ptPhoneDoctorManager.add(ptPhoneDoctor);
            ptGoods.setYouPhoneDoctorId(ptPhoneDoctor.getId());
        } else {
            ptGoods.setYouPhoneDoctorId(0L);
        }

        PtGoodsTemp ptGoodsTemp = new PtGoodsTemp();
        BeanUtil.copyProperties(ptGoods, ptGoodsTemp, "id");
        ptGoodsTemp.setReason(Constant.REASON_NEW_CREATE);
        ptGoodsTemp.setOperatorType(Constant.REASON_NEW_CREATE);
        ptGoodsTemp.setGoodsId(ptGoods.getId());
        ptGoodsManager.addTemp(ptGoodsTemp);

        return ptGoodsManager.add(ptGoods);
    }

    /**
     * 更新后，原值不操作，只操作temp表
     */
    public PtGoodsTemp update(GoodsAddUpdateModel goodsAddMode) {
        PtGoodsTemp ptGoodsTemp = ptGoodsManager.findOneTempByGoodsId(goodsAddMode.getId());
        if (ptGoodsTemp == null) {
            ptGoodsTemp = new PtGoodsTemp();
            ptGoodsTemp.setGoodsId(goodsAddMode.getId());
        }

        PtCashPrePay cashPrePay = goodsAddMode.getPtCashPrePay();
        if (cashPrePay != null) {
            cashPrePay.setGoodsId(goodsAddMode.getId());
            cashPrePay = ptCashPrePayManager.add(cashPrePay);
            ptGoodsTemp.setYouCashPrePayId(cashPrePay.getId());
        } else {
            ptGoodsTemp.setYouCashPrePayId(0L);
        }

        PtPhoneDoctor ptPhoneDoctor = goodsAddMode.getPtPhoneDoctor();
        if (ptPhoneDoctor != null) {
            ptPhoneDoctor.setGoodsId(goodsAddMode.getId());
            ptPhoneDoctor = ptPhoneDoctorManager.add(ptPhoneDoctor);
            ptGoodsTemp.setYouPhoneDoctorId(ptPhoneDoctor.getId());
        } else {
            ptGoodsTemp.setYouPhoneDoctorId(0L);
        }

        BeanUtil.copyProperties(goodsAddMode, ptGoodsTemp, "id");
        ptGoodsTemp.setReason(goodsAddMode.getReason());
        ptGoodsTemp.setStatus(Constant.STATE_CONFIRM);
        ptGoodsTemp.setOperatorType(Constant.REASON_UPDATE);

        return ptGoodsManager.updateTemp(ptGoodsTemp);
    }

    /**
     * 审核商品，是否同意
     *
     * @param id
     *         tempId
     * @param confirm
     *         confirm
     */
    public void confirm(Long id, Boolean confirm) {
        if (confirm == null) {
            return;
        }
        PtGoodsTemp goodsTemp = ptGoodsManager.findOneTemp(id);
        PtGoods ptGoods = ptGoodsManager.findOne(goodsTemp.getGoodsId());
        //如果是下架
        if (Constant.REASON_DELETE.equals(goodsTemp.getOperatorType())) {
            if (confirm) {
                //确认下架，将deleteFlag置为true
                ptGoodsManager.delete(ptGoods);
                goodsTemp.setStatus(Constant.STATE_NORMAL);
            } else {
                goodsTemp.setStatus(Constant.STATE_REFUSE);
            }
        } else { //新建、修改相关的
            if (confirm) {
                goodsTemp.setStatus(Constant.STATE_NORMAL);
                //将更新后的覆盖到原来的里面
                BeanUtil.copyProperties(goodsTemp, ptGoods, "id");
                ptGoodsManager.update(ptGoods);
            } else {
                goodsTemp.setStatus(Constant.STATE_REFUSE);
            }
        }
        ptGoodsManager.updateTemp(goodsTemp);
    }

    public PtGoods findOne(Long id) {
        return ptGoodsManager.findOne(id);
    }

    /**
     * 包含被修改的详情
     */
    public GoodsModifyDetailVO findDetail(Long id) {
        PtGoodsTemp goodsTemp = ptGoodsManager.findOneTemp(id);
        PtGoods ptGoods = ptGoodsManager.findOne(goodsTemp.getGoodsId());

        GoodsModifyDetailVO companyModifyDetailVO = new GoodsModifyDetailVO();
        companyModifyDetailVO.setOperatorName(ptUserManager.findNameById(goodsTemp.getOperatorId()));
        companyModifyDetailVO.setOrignal(ptGoods);
        companyModifyDetailVO.setModified(goodsTemp);

        return companyModifyDetailVO;
    }

    public PtCashPrePay findByCashPrePayId(Long id) {
        return ptCashPrePayManager.find(id);
    }

    public PtPhoneDoctor findByPhoneDoctorId(Long id) {
        return ptPhoneDoctorManager.find(id);
    }

    /**
     * 查待确认的，所有的商品
     */
    public SimplePage<GoodsTempListVO> find(GoodsTempListQueryModel goodsTempListQueryModel) {
        Criteria<PtGoodsTemp> criteria = new Criteria<>();
        criteria.add(Restrictions.like("name", goodsTempListQueryModel.getName(), true));
        criteria.add(Restrictions.eq("status", goodsTempListQueryModel.getStatus(), true));
        criteria.add(Restrictions.eq("operatorId", goodsTempListQueryModel.getOperatorId(), true));

        Pageable pageable = PageRequest.of(goodsTempListQueryModel.getPage(), goodsTempListQueryModel
                .getSize());
        Page<PtGoodsTemp> page = ptGoodsManager.findAllTemp(criteria, pageable);

        return new SimplePage<>(page.getTotalPages(), page.getTotalElements(), page.getContent().stream().map
                (this::parse).collect(Collectors.toList()));
    }

    public SimplePage<GoodsListVO> findByCompanyId(Long companyId, Pageable pageable) {
        Page<PtGoods> goodsPage = ptGoodsManager.findByCompanyId(companyId, pageable);
        return new SimplePage<>(goodsPage.getTotalPages(), goodsPage.getTotalElements(), goodsPage.getContent()
                .stream().map(this::parse).collect(Collectors.toList()));
    }

    /**
     * 下架所有某planId的temp
     *
     * @param planId
     *         planId
     */
    public void deleteTempByPlanId(Long planId) {
        List<PtGoodsTemp> temps = ptGoodsManager.findByPlanId(planId);
        for (PtGoodsTemp temp : temps) {
            ptGoodsManager.deleteTemp(temp);
        }
    }

    /**
     * 下架某个商品，则先操作temp表，该表不动
     *
     * @param id
     *         id
     */
    public void deleteById(Long id) {
        PtGoodsTemp temp = ptGoodsManager.findOneTempByGoodsId(id);
        temp.setStatus(Constant.STATE_CONFIRM);
        ptGoodsManager.deleteTemp(temp);
    }

    private GoodsTempListVO parse(PtGoodsTemp temp) {
        GoodsTempListVO tempListVO = new GoodsTempListVO();
        BeanUtil.copyProperties(temp, tempListVO);
        tempListVO.setPtGoodsPlanName(ptGoodsPlanManager.findNameById(temp.getPtGoodsPlanId()));
        tempListVO.setOperatorName(ptUserManager.findNameById(temp.getOperatorId()));

        return tempListVO;
    }

    private GoodsListVO parse(PtGoods ptGoods) {
        GoodsListVO vo = new GoodsListVO();
        BeanUtil.copyProperties(ptGoods, vo);
        vo.setPtGoodsPlanName(ptGoodsPlanManager.findNameById(ptGoods.getPtGoodsPlanId()));
        vo.setYouServers(ptGoodsManager.youServers(ptGoods));

        return vo;
    }



}
