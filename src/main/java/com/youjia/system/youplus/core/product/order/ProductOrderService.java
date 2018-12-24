package com.youjia.system.youplus.core.product.order;

import com.xiaoleilu.hutool.util.BeanUtil;
import com.youjia.system.youplus.core.company.company.PtCompanyManager;
import com.youjia.system.youplus.core.company.goods.PtGoods;
import com.youjia.system.youplus.core.company.goods.PtGoodsManager;
import com.youjia.system.youplus.core.company.youserver.PtCashPrePay;
import com.youjia.system.youplus.core.company.youserver.PtCashPrePayManager;
import com.youjia.system.youplus.core.dict.area.AreaManager;
import com.youjia.system.youplus.core.medical.hospital.PtHospitalManager;
import com.youjia.system.youplus.core.order.OrderService;
import com.youjia.system.youplus.core.order.PtOrder;
import com.youjia.system.youplus.core.person.GroundPersonService;
import com.youjia.system.youplus.core.person.PtGroundPersonManager;
import com.youjia.system.youplus.core.product.PtProductManager;
import com.youjia.system.youplus.core.product.flow.PtOrderFlow;
import com.youjia.system.youplus.core.product.flow.PtOrderFlowManager;
import com.youjia.system.youplus.core.product.template.prepay.PtPrePayTemplate;
import com.youjia.system.youplus.core.product.template.prepay.PtPrePayTemplateManager;
import com.youjia.system.youplus.global.UserKit;
import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import com.youjia.system.youplus.global.bean.SimplePage;
import com.youjia.system.youplus.global.bean.request.OrderListQueryModel;
import com.youjia.system.youplus.global.bean.request.ProductOrderAddModel;
import com.youjia.system.youplus.global.bean.request.ProductOrderListQueryModel;
import com.youjia.system.youplus.global.bean.response.*;
import com.youjia.system.youplus.global.cache.DictCache;
import com.youjia.system.youplus.global.specify.Criteria;
import com.youjia.system.youplus.global.specify.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 派单功能
 *
 * @author wuweifeng wrote on 2018/11/12.
 */
@Component
@Transactional(rollbackFor = Exception.class)
public class ProductOrderService {
    @Resource
    private PtProductOrderManager ptProductOrderManager;
    @Resource
    private PtProductManager ptProductManager;
    @Resource
    private PtPrePayTemplateManager ptPrePayTemplateManager;
    @Resource
    private OrderService orderService;
    @Resource
    private DictCache dictCache;
    @Resource
    private AreaManager areaManager;
    @Resource
    private PtCompanyManager ptCompanyManager;
    @Resource
    private PtGroundPersonManager ptGroundPersonManager;
    @Resource
    private PtHospitalManager ptHospitalManager;
    @Resource
    private GroundPersonService groundPersonService;
    @Resource
    private PtOrderFlowManager ptOrderFlowManager;
    @Resource
    private PtGoodsManager ptGoodsManager;
    @Resource
    private PtCashPrePayManager ptCashPrePayManager;
    @Resource
    private UserKit userKit;


    public BaseData add(ProductOrderAddModel productOrderAddModel) {
        PtPrePayTemplate template = productOrderAddModel.getTemplate();
        if (template == null) {
            return ResultGenerator.genFailResult("预约信息不能为空");
        }

        PtProductOrder ptProductOrder = new PtProductOrder();
        BeanUtil.copyProperties(productOrderAddModel, ptProductOrder);
        //未派单
        ptProductOrder.setState("1");
        //未转单
        ptProductOrder.setChildState("1");
        ptProductOrder = ptProductOrderManager.add(ptProductOrder);

        template.setProductId(ptProductOrder.getId());
        template = ptPrePayTemplateManager.add(template);
        ptProductOrder.setTemplateId(template.getId());

        ptProductOrderManager.update(ptProductOrder);

        return ResultGenerator.genSuccessResult(ptProductOrder);
    }

    public PtProductOrder update(ProductOrderAddModel productOrderAddModel) {
        Long id = productOrderAddModel.getId();
        PtProductOrder ptProductOrder = ptProductOrderManager.find(id);
        if (ptProductOrder == null) {
            return null;
        }
        BeanUtil.copyProperties(productOrderAddModel, ptProductOrder, BeanUtil.CopyOptions.create()
                .setIgnoreNullValue(true));

        PtPrePayTemplate template = productOrderAddModel.getTemplate();
        if (template != null) {
            PtPrePayTemplate originalTemplate = ptPrePayTemplateManager.find(productOrderAddModel.getTemplateId());
            if (originalTemplate == null) {
                originalTemplate = new PtPrePayTemplate();
            }
            BeanUtil.copyProperties(template, originalTemplate, BeanUtil.CopyOptions.create().setIgnoreNullValue(true));
            ptPrePayTemplateManager.update(originalTemplate);

            ptProductOrder.setTemplateId(originalTemplate.getId());
        }

        return ptProductOrderManager.update(ptProductOrder);
    }

    /**
     * 查询某一个
     */
    public ProductOrderVO find(Long id) {
        ProductOrderVO productOrderVO = new ProductOrderVO();
        PtProductOrder ptProductOrder = ptProductOrderManager.find(id);
        PtPrePayTemplate template = ptPrePayTemplateManager.find(ptProductOrder.getTemplateId());
        productOrderVO.setTemplate(parseTemplate(template));

        productOrderVO.setProductId(ptProductOrder.getProductId());
        productOrderVO.setProductName(ptProductManager.findNameById(ptProductOrder.getProductId()));

        OrderListVO orderListVO = orderService.parse(ptProductOrder.getOrderId());
        productOrderVO.setOrderListVO(orderListVO);

        GroundPersonListVO groundPersonListVO = groundPersonService.parse(ptProductOrder.getGroundPersonId());
        productOrderVO.setGroundPersonListVO(groundPersonListVO);

        //订单的详细流程
        PtOrderFlow orderFlow = ptOrderFlowManager.findByProductOrderId(id);
        productOrderVO.setOrderFlow(orderFlow);

        return productOrderVO;
    }

    public CashPrePayWordsVO findWords(Long id) {
        PtProductOrder ptProductOrder = ptProductOrderManager.find(id);
        PtOrder ptOrder = orderService.findOne(ptProductOrder.getOrderId());
        Long goodsId = ptOrder.getPtGoodsId();
        PtGoods ptGoods = ptGoodsManager.findOne(goodsId);
        CashPrePayWordsVO vo = new CashPrePayWordsVO();
        Long prepayId = ptGoods.getYouCashPrePayId();
        if (prepayId != 0) {
            PtCashPrePay ptCashPrePay = ptCashPrePayManager.find(prepayId);
            vo.setFilePaths(ptCashPrePay.getFilePaths());
            vo.setRemark(ptCashPrePay.getRemark());
        }
        return vo;
    }

    private PrePayTemplateVO parseTemplate(PtPrePayTemplate template) {
        PrePayTemplateVO vo = new PrePayTemplateVO();
        BeanUtil.copyProperties(template, vo);
        vo.setProvinceValue(areaManager.findName(template.getProvince()));
        vo.setCityValue(areaManager.findName(template.getCity()));
        vo.setCountryValue(areaManager.findName(template.getCountry()));
        vo.setWantProvinceValue(areaManager.findName(template.getWantProvince()));
        vo.setWantCityValue(areaManager.findName(template.getWantCity()));
        vo.setWantCountryValue(areaManager.findName(template.getWantCountry()));
        vo.setSheBaoCityValue(areaManager.findName(template.getSheBaoCity()));
        vo.setSheBaoCountryValue(areaManager.findName(template.getSheBaoCountry()));
        vo.setSheBaoProvinceValue(areaManager.findName(template.getSheBaoProvince()));
        vo.setHospitalName(ptHospitalManager.findName(template.getHospitalId()));
        vo.setDept1Value(dictCache.findByGroupIdAndKey(1, template.getDept1()));
        vo.setDept2Value(dictCache.findByGroupIdAndKey(2, template.getDept2()));
        vo.setSheBaoStateValue(dictCache.findByGroupIdAndKey(13, template.getSheBaoState()));
        return vo;
    }

    public PtProductOrder chooseGroundPerson(Long id, Long personId, String remark) {
        PtProductOrder ptProductOrder = ptProductOrderManager.find(id);
        ptProductOrder.setRemark(remark);
        ptProductOrder.setGroundPersonId(personId);
        ptProductOrder.setState("2");
        return ptProductOrderManager.update(ptProductOrder);
    }

    /**
     * 转单
     */
    public PtProductOrder changeGroundPerson(Long id, Long personId, String remark) {
        PtProductOrder ptProductOrder = ptProductOrderManager.find(id);
        ptProductOrder.setRemark(remark);
        ptProductOrder.setGroundPersonId(personId);
        ptProductOrder.setState("2");
        ptProductOrder.setChildState("2");
        return ptProductOrderManager.update(ptProductOrder);
    }

    /**
     * 所有的
     */
    public SimplePage<ProductOrderListVO> findAll(ProductOrderListQueryModel productOrderListQueryModel) {
        Criteria<PtProductOrder> criteria = new Criteria<>();
        criteria.add(Restrictions.eq("id", productOrderListQueryModel.getId(), true));
        criteria.add(Restrictions.eq("state", productOrderListQueryModel.getState(), true));
        criteria.add(Restrictions.ne("state", productOrderListQueryModel.getNotState(), true));
        criteria.add(Restrictions.eq("childState", productOrderListQueryModel.getChildState(), true));
        criteria.add(Restrictions.eq("deleteFlag", false, true));
        //给H5用的功能
        if (userKit.getGroundPersonId() != null) {
            criteria.add(Restrictions.eq("groundPersonId", userKit.getGroundPersonId(), true));
        }

        OrderListQueryModel orderListQueryModel = new OrderListQueryModel();
        BeanUtil.copyProperties(productOrderListQueryModel, orderListQueryModel);
        if (!isAllFieldNull(orderListQueryModel)) {
            List<Long> ids = orderService.findIds(orderListQueryModel);
            criteria.add(Restrictions.in("orderId", ids, false));
        }

        Pageable pageable = PageRequest.of(productOrderListQueryModel.getPage(), productOrderListQueryModel.getSize(),
                Sort.Direction.DESC, "id");
        Page<PtProductOrder> page = ptProductOrderManager.findAll(criteria, pageable);

        return new SimplePage<>(page.getTotalPages(), page.getTotalElements(), page.getContent().stream().map
                (this::parse).collect(Collectors.toList()));
    }

    private ProductOrderListVO parse(PtProductOrder ptProductOrder) {
        ProductOrderListVO productOrderListVO = new ProductOrderListVO();

        PtOrder order = orderService.findOne(ptProductOrder.getOrderId());
        BeanUtil.copyProperties(order, productOrderListVO);

        BeanUtil.copyProperties(ptProductOrder, productOrderListVO);
        productOrderListVO.setChildStateValue(dictCache.findByGroupIdAndKey(15, ptProductOrder.getChildState()));
        productOrderListVO.setStateValue(dictCache.findByGroupIdAndKey(14, ptProductOrder.getState()));

        productOrderListVO.setCityValue(areaManager.findName(order.getCity()));
        productOrderListVO.setProvinceValue(areaManager.findName(order.getProvince()));
        productOrderListVO.setCountryValue(areaManager.findName(order.getCountry()));

        productOrderListVO.setCompanyName(ptCompanyManager.findNameById(order.getCompanyId()));
        productOrderListVO.setGroundPersonName(ptGroundPersonManager.findNameById(ptProductOrder.getGroundPersonId()));
        productOrderListVO.setHospitalName(ptHospitalManager.findName(ptPrePayTemplateManager.find(ptProductOrder
                .getTemplateId()).getHospitalId()));

        return productOrderListVO;
    }

    /**
     * 判断该对象是否: 返回true表示所有属性为null  返回false表示不是所有属性都是null
     */
    private boolean isAllFieldNull(Object obj) {
        Class stuCla = obj.getClass();
        Field[] fs = stuCla.getDeclaredFields();
        boolean flag = true;
        //遍历属性
        for (Field f : fs) {
            // 设置属性是可以访问的(私有的也可以)
            f.setAccessible(true);
            // 得到此属性的值
            Object val = null;
            try {
                val = f.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            //只要有1个属性不为空,那么就不是所有的属性值都为空
            if (val != null) {
                flag = false;
                break;
            }
        }
        return flag;
    }


    public void delete(Long id) {
        PtProductOrder ptProductOrder = ptProductOrderManager.find(id);
        ptProductOrder.setDeleteFlag(true);
        ptProductOrderManager.update(ptProductOrder);
    }

}
