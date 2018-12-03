package com.youjia.system.youplus.core.order;

import com.xiaoleilu.hutool.util.BeanUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import com.youjia.system.youplus.core.company.company.PtCompanyManager;
import com.youjia.system.youplus.core.company.goods.PtGoodsManager;
import com.youjia.system.youplus.core.dict.area.AreaManager;
import com.youjia.system.youplus.core.user.user.PtUserManager;
import com.youjia.system.youplus.global.bean.SimplePage;
import com.youjia.system.youplus.global.bean.request.OrderAddUpdateModel;
import com.youjia.system.youplus.global.bean.request.OrderListQueryModel;
import com.youjia.system.youplus.global.bean.request.OrderTempListQueryModel;
import com.youjia.system.youplus.global.bean.response.OrderListVO;
import com.youjia.system.youplus.global.bean.response.OrderModifyDetailVO;
import com.youjia.system.youplus.global.bean.response.OrderTempListVO;
import com.youjia.system.youplus.global.specify.Criteria;
import com.youjia.system.youplus.global.specify.Restrictions;
import com.youjia.system.youplus.global.util.Constant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
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
        if (ptOrderRelation != null) {
            ptOrderRelation.setOrderId(ptOrder.getId());
            ptOrderRelationManager.add(ptOrderRelation);
        }

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


    /**
     * 查询某用户的所有保单列表
     * @param id id
     * @return List
     */
    public List<OrderListVO> findListByUserPaper(Long id) {
        PtOrder ptOrder = findOne(id);
        String paper = ptOrder.getPaper();
        if (StrUtil.isEmpty(paper)) {
            return new ArrayList<>();
        }
        Criteria<PtOrder> criteria = new Criteria<>();
        criteria.add(Restrictions.eq("status", Constant.STATE_NORMAL, true));
        criteria.add(Restrictions.like("paper", paper, true));
        criteria.add(Restrictions.eq("deleteFlag", false, true));
        Pageable pageable = PageRequest.of(0, 1000, Sort
                .Direction.DESC, "id");
        Page<PtOrder> page = ptOrderManager.findAll(criteria, pageable);
        return page.getContent().stream().map(this::parse).collect(Collectors.toList());
    }

    /**
     * 下架某个服务单，则先操作temp表，该表不动
     *
     * @param id
     *         id
     */
    public void deleteById(Long id, String reason) {
        PtOrderTemp temp = ptOrderManager.findOneTempByOrderId(id);
        temp.setStatus(Constant.STATE_CONFIRM);
        temp.setReason(reason);
        ptOrderManager.deleteTemp(temp);
    }

    /**
     * 包含被修改的详情
     */
    public OrderModifyDetailVO findDetail(Long id) {
        PtOrderTemp orderTemp = ptOrderManager.findOneTemp(id);
        PtOrder ptGoods = ptOrderManager.findOne(orderTemp.getOrderId());

        OrderModifyDetailVO orderModifyDetailVO = new OrderModifyDetailVO();
        orderModifyDetailVO.setOperatorName(ptUserManager.findNameById(orderTemp.getOperatorId()));
        orderModifyDetailVO.setOrignal(ptGoods);
        orderModifyDetailVO.setModified(orderTemp);

        return orderModifyDetailVO;
    }

    public PtOrderTemp update(OrderAddUpdateModel orderAddUpdateModel) {
        //看有没有在修改中的，有则取，无则新建
        PtOrderTemp ptOrderTemp = ptOrderManager.findTempByOrderId(orderAddUpdateModel.getId());
        if (ptOrderTemp == null) {
            ptOrderTemp = new PtOrderTemp();
            ptOrderTemp.setOrderId(orderAddUpdateModel.getId());
        }
        BeanUtil.copyProperties(orderAddUpdateModel, ptOrderTemp, "id");
        ptOrderTemp.setStatus(Constant.STATE_CONFIRM);
        ptOrderTemp.setReason(orderAddUpdateModel.getReason());
        ptOrderTemp.setOperatorType(Constant.REASON_UPDATE);

        PtOrderRelation ptOrderRelation = orderAddUpdateModel.getOrderRelation();
        if (ptOrderRelation != null) {
            ptOrderRelationManager.update(ptOrderRelation);
        }
        return ptOrderManager.updateTemp(ptOrderTemp);
    }

    /**
     * 查已通过的
     */
    public SimplePage<OrderListVO> find(OrderListQueryModel companyListQueryModel) {
        Criteria<PtOrder> criteria = new Criteria<>();
        criteria.add(Restrictions.eq("companyId", companyListQueryModel.getCompanyId(), true));
        criteria.add(Restrictions.eq("status", Constant.STATE_NORMAL, true));
        criteria.add(Restrictions.like("userName", companyListQueryModel.getUserName(), true));
        criteria.add(Restrictions.like("cardNum", companyListQueryModel.getCardNum(), true));
        criteria.add(Restrictions.like("mobile", companyListQueryModel.getMobile(), true));
        criteria.add(Restrictions.like("paper", companyListQueryModel.getPaper(), true));
        criteria.add(Restrictions.eq("province", companyListQueryModel.getProvince(), true));
        criteria.add(Restrictions.eq("city", companyListQueryModel.getCity(), true));
        criteria.add(Restrictions.eq("country", companyListQueryModel.getCountry(), true));
        criteria.add(Restrictions.eq("deleteFlag", false, true));

        Pageable pageable = PageRequest.of(companyListQueryModel.getPage(), companyListQueryModel.getSize(), Sort
                .Direction.DESC, "id");
        Page<PtOrder> page = ptOrderManager.findAll(criteria, pageable);

        return new SimplePage<>(page.getTotalPages(), page.getTotalElements(), page.getContent().stream().map
                (this::parse).collect(Collectors.toList()));
    }

    /**
     * 查待确认的，所有的
     */
    public SimplePage<OrderTempListVO> findTemp(OrderTempListQueryModel orderTempListQueryModel) {
        Criteria<PtOrderTemp> criteria = new Criteria<>();
        criteria.add(Restrictions.eq("status", orderTempListQueryModel.getStatus(), true));
        criteria.add(Restrictions.eq("operatorId", orderTempListQueryModel.getOperatorId(), true));
        criteria.add(Restrictions.like("userName", orderTempListQueryModel.getUserName(), true));
        criteria.add(Restrictions.like("mobile", orderTempListQueryModel.getMobile(), true));

        Pageable pageable = PageRequest.of(orderTempListQueryModel.getPage(), orderTempListQueryModel.getSize(),
                Sort.Direction.DESC, "updateTime");
        Page<PtOrderTemp> page = ptOrderManager.findAllTemp(criteria, pageable);

        return new SimplePage<>(page.getTotalPages(), page.getTotalElements(), page.getContent().stream().map
                (this::parseTemp).collect(Collectors.toList()));
    }

    /**
     * 审核服务单，是否同意
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
        PtOrderTemp oneTemp = ptOrderManager.findOneTemp(id);
        PtOrder ptOrder = ptOrderManager.findOne(oneTemp.getOrderId());
        //如果是下架
        if (Constant.REASON_DELETE.equals(oneTemp.getOperatorType())) {
            if (confirm) {
                //确认下架，将deleteFlag置为true
                ptOrderManager.delete(ptOrder);
                oneTemp.setStatus(Constant.STATE_NORMAL);
            } else {
                oneTemp.setStatus(Constant.STATE_REFUSE);
            }
        } else { //新建、修改相关的
            if (confirm) {
                oneTemp.setStatus(Constant.STATE_NORMAL);
                //将更新后的覆盖到原来的里面
                BeanUtil.copyProperties(oneTemp, ptOrder, "id");
                ptOrderManager.update(ptOrder);
            } else {
                oneTemp.setStatus(Constant.STATE_REFUSE);
            }
        }
        ptOrderManager.updateTemp(oneTemp);
    }

    private OrderListVO parse(PtOrder ptOrder) {
        OrderListVO orderListVO = new OrderListVO();
        BeanUtil.copyProperties(ptOrder, orderListVO);
        orderListVO.setGoodsName(ptGoodsManager.findNameById(ptOrder.getPtGoodsId()));
        orderListVO.setYouServers(ptGoodsManager.youServers(ptOrder.getPtGoodsId()));
        orderListVO.setCompanyName(ptCompanyManager.findNameById(ptOrder.getCompanyId()));
        orderListVO.setProvince(ptOrder.getProvince());
        orderListVO.setCity(ptOrder.getCity());
        orderListVO.setCountry(ptOrder.getCountry());
        return orderListVO;
    }

    private OrderTempListVO parseTemp(PtOrderTemp ptOrderTemp) {
        OrderTempListVO tempListVO = new OrderTempListVO();
        BeanUtil.copyProperties(ptOrderTemp, tempListVO);
        tempListVO.setOperatorName(ptUserManager.findNameById(ptOrderTemp.getOperatorId()));
        return tempListVO;
    }
}
