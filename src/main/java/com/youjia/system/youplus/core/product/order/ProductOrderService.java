package com.youjia.system.youplus.core.product.order;

import com.xiaoleilu.hutool.util.BeanUtil;
import com.youjia.system.youplus.core.company.company.PtCompanyManager;
import com.youjia.system.youplus.core.dict.area.AreaManager;
import com.youjia.system.youplus.core.medical.hospital.PtHospitalManager;
import com.youjia.system.youplus.core.order.OrderService;
import com.youjia.system.youplus.core.order.PtOrder;
import com.youjia.system.youplus.core.person.PtGroundPersonManager;
import com.youjia.system.youplus.core.product.PtProductManager;
import com.youjia.system.youplus.core.product.template.prepay.PtPrePayTemplate;
import com.youjia.system.youplus.core.product.template.prepay.PtPrePayTemplateManager;
import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import com.youjia.system.youplus.global.bean.SimplePage;
import com.youjia.system.youplus.global.bean.request.OrderListQueryModel;
import com.youjia.system.youplus.global.bean.request.ProductOrderAddModel;
import com.youjia.system.youplus.global.bean.request.ProductOrderListQueryModel;
import com.youjia.system.youplus.global.bean.response.OrderListVO;
import com.youjia.system.youplus.global.bean.response.ProductOrderListVO;
import com.youjia.system.youplus.global.bean.response.ProductOrderVO;
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


    public BaseData add(ProductOrderAddModel productOrderAddModel) {
        PtPrePayTemplate template = productOrderAddModel.getTemplate();
        if (template == null) {
            return ResultGenerator.genFailResult("预约信息不能为空");
        }

        PtProductOrder ptProductOrder = new PtProductOrder();
        BeanUtil.copyProperties(productOrderAddModel, ptProductOrder);
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
        BeanUtil.copyProperties(productOrderAddModel, ptProductOrder);

        PtPrePayTemplate template = productOrderAddModel.getTemplate();
        if (template != null) {
            PtPrePayTemplate orignalTemplate = ptPrePayTemplateManager.find(productOrderAddModel.getTemplateId());
            if (orignalTemplate == null) {
                orignalTemplate = new PtPrePayTemplate();
            }
            BeanUtil.copyProperties(template, orignalTemplate, BeanUtil.CopyOptions.create().setIgnoreNullValue(true));
            ptPrePayTemplateManager.update(orignalTemplate);

            ptProductOrder.setTemplateId(orignalTemplate.getId());
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
        productOrderVO.setTemplate(template);

        productOrderVO.setProductId(ptProductOrder.getProductId());
        productOrderVO.setProductName(ptProductManager.findNameById(ptProductOrder.getProductId()));

        OrderListVO orderListVO = orderService.parse(ptProductOrder.getOrderId());
        productOrderVO.setOrderListVO(orderListVO);

        return productOrderVO;
    }

    /**
     * 查待确认的，所有的
     */
    public SimplePage<ProductOrderListVO> findAll(ProductOrderListQueryModel productOrderListQueryModel) {
        Criteria<PtProductOrder> criteria = new Criteria<>();
        criteria.add(Restrictions.eq("id", productOrderListQueryModel.getId(), true));
        criteria.add(Restrictions.eq("state", productOrderListQueryModel.getState(), true));
        criteria.add(Restrictions.eq("childState", productOrderListQueryModel.getChildState(), true));

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
        BeanUtil.copyProperties(ptProductOrder, productOrderListVO);
        productOrderListVO.setChildStateValue(dictCache.findByGroupIdAndKey(15, ptProductOrder.getChildState()));
        productOrderListVO.setStateValue(dictCache.findByGroupIdAndKey(14, ptProductOrder.getState()));

        PtOrder order = orderService.findOne(ptProductOrder.getOrderId());
        BeanUtil.copyProperties(order, productOrderListVO);

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
     * 判断该对象是否: 返回ture表示所有属性为null  返回false表示不是所有属性都是null
     */
    private boolean isAllFieldNull(Object obj) {
        Class stuCla = (Class) obj.getClass();
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
        ptProductOrderManager.delete(ptProductOrderManager.find(id));
    }

}
