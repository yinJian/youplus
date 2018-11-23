package com.youjia.system.youplus.core.order;

import com.xiaoleilu.hutool.util.BeanUtil;
import com.youjia.system.youplus.core.company.company.PtCompanyManager;
import com.youjia.system.youplus.core.company.goods.PtGoodsManager;
import com.youjia.system.youplus.core.dict.area.AreaManager;
import com.youjia.system.youplus.core.user.user.PtUserManager;
import com.youjia.system.youplus.global.bean.SimplePage;
import com.youjia.system.youplus.global.bean.request.OrderAddUpdateModel;
import com.youjia.system.youplus.global.bean.request.OrderListQueryModel;
import com.youjia.system.youplus.global.bean.response.OrderListVO;
import com.youjia.system.youplus.global.bean.response.OrderTempListVO;
import com.youjia.system.youplus.global.specify.Criteria;
import com.youjia.system.youplus.global.specify.Restrictions;
import com.youjia.system.youplus.global.util.Constant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * @author wuweifeng wrote on 2018/11/22.
 */
@Component
public class OrderService {
    @Resource
    private PtOrderManager ptOrderManager;
    @Resource
    private PtGoodsManager ptGoodsManager;
    @Resource
    private PtCompanyManager ptCompanyManager;
    @Resource
    private PtUserManager ptUserManager;
    @Resource
    private AreaManager areaManager;
    @Resource
    private PtOrderRelationManager ptOrderRelationManager;

    public PtOrder add(OrderAddUpdateModel orderAddUpdateModel, boolean needConfirm) {
        PtOrder ptOrder = new PtOrder();
        BeanUtil.copyProperties(orderAddUpdateModel, ptOrder);

        //同时新建一个temp
        PtOrderTemp ptOrderTemp = new PtOrderTemp();
        BeanUtil.copyProperties(orderAddUpdateModel, ptOrderTemp, "id");
        ptOrderTemp.setReason(Constant.REASON_NEW_CREATE);
        ptOrderTemp.setOperatorType(Constant.REASON_NEW_CREATE);

        if (needConfirm) {
            ptOrder.setStatus(Constant.STATE_CONFIRM);
            ptOrderTemp.setStatus(Constant.STATE_CONFIRM);
        } else {
            ptOrder.setStatus(Constant.STATE_NORMAL);
            ptOrderTemp.setStatus(Constant.STATE_NORMAL);
        }

        ptOrder = ptOrderManager.add(ptOrder);

        ptOrderTemp.setOrderId(ptOrder.getId());
        ptOrderManager.addTemp(ptOrderTemp);

        PtOrderRelation ptOrderRelation = orderAddUpdateModel.getOrderRelation();
        ptOrderRelation.setOrderId(ptOrder.getId());
        ptOrderRelationManager.add(ptOrderRelation);

        return ptOrder;
    }

    /**
     * 查询order详情
     *
     * @param id
     *         id
     * @return PtOrder
     */
    public PtOrder findOne(Long id) {
        return ptOrderManager.findOne(id);
    }

    public PtOrderTemp update(PtOrder ptOrder, String reason) {
        //看有没有在修改中的，有则取，无则新建
        PtOrderTemp ptOrderTemp = ptOrderManager.findTempByOrderId(ptOrder.getId());
        if (ptOrderTemp == null) {
            ptOrderTemp = new PtOrderTemp();
            ptOrderTemp.setOrderId(ptOrder.getId());
        }
        BeanUtil.copyProperties(ptOrder, ptOrderTemp, "id");
        ptOrderTemp.setStatus(Constant.STATE_CONFIRM);
        ptOrderTemp.setReason(reason);
        ptOrderTemp.setOperatorType(Constant.REASON_UPDATE);
        return ptOrderManager.updateTemp(ptOrderTemp);
    }

    /**
     * 查已通过的
     */
    public SimplePage<OrderListVO> find(OrderListQueryModel companyListQueryModel) {
        Criteria<PtOrder> criteria = new Criteria<>();
        criteria.add(Restrictions.like("userName", companyListQueryModel.getUserName(), true));
        criteria.add(Restrictions.like("cardNum", companyListQueryModel.getCardNum(), true));
        criteria.add(Restrictions.like("mobile", companyListQueryModel.getMobile(), true));
        criteria.add(Restrictions.like("paper", companyListQueryModel.getPaper(), true));
        criteria.add(Restrictions.eq("companyId", companyListQueryModel.getCompanyId(), true));
        criteria.add(Restrictions.eq("status", Constant.STATE_NORMAL, true));

        Pageable pageable = PageRequest.of(companyListQueryModel.getPage(), companyListQueryModel.getSize());
        Page<PtOrder> page = ptOrderManager.findAll(criteria, pageable);

        return new SimplePage<>(page.getTotalPages(), page.getTotalElements(), page.getContent().stream().map
                (this::parse).collect(Collectors.toList()));
    }

    /**
     * 查待确认的，所有的
     */
    public SimplePage<OrderTempListVO> findTemp(OrderListQueryModel orderListQueryModel) {
        Criteria<PtOrderTemp> criteria = new Criteria<>();
        criteria.add(Restrictions.like("userName", orderListQueryModel.getUserName(), true));
        criteria.add(Restrictions.like("mobile", orderListQueryModel.getMobile(), true));
        criteria.add(Restrictions.eq("status", orderListQueryModel.getStatus(), true));
        criteria.add(Restrictions.eq("operatorId", orderListQueryModel.getOperatorId(), true));

        Pageable pageable = PageRequest.of(orderListQueryModel.getPage(), orderListQueryModel.getSize());
        Page<PtOrderTemp> page = ptOrderManager.findAllTemp(criteria, pageable);

        return new SimplePage<>(page.getTotalPages(), page.getTotalElements(), page.getContent().stream().map
                (this::parseTemp).collect(Collectors.toList()));
    }

    private OrderListVO parse(PtOrder ptOrder) {
        OrderListVO orderListVO = new OrderListVO();
        BeanUtil.copyProperties(ptOrder, orderListVO);
        orderListVO.setGoodsName(ptGoodsManager.findNameById(ptOrder.getPtGoodsId()));
        orderListVO.setYouServers(ptGoodsManager.youServers(ptOrder.getPtGoodsId()));
        orderListVO.setCompanyName(ptCompanyManager.findNameById(ptOrder.getCompanyId()));
        orderListVO.setArea(areaManager.findFull(ptOrder.getAreaCode()));
        return orderListVO;
    }

    private OrderTempListVO parseTemp(PtOrderTemp ptOrderTemp) {
        OrderTempListVO tempListVO = new OrderTempListVO();
        BeanUtil.copyProperties(ptOrderTemp, tempListVO);
        tempListVO.setOperatorName(ptUserManager.findNameById(ptOrderTemp.getOperatorId()));
        return tempListVO;
    }
}
