package com.youjia.system.youplus.core.product.order;

import com.xiaoleilu.hutool.util.BeanUtil;
import com.youjia.system.youplus.core.order.OrderService;
import com.youjia.system.youplus.core.product.PtProductManager;
import com.youjia.system.youplus.core.product.template.prepay.PtPrePayTemplate;
import com.youjia.system.youplus.core.product.template.prepay.PtPrePayTemplateManager;
import com.youjia.system.youplus.global.bean.request.ProductOrderAddModel;
import com.youjia.system.youplus.global.bean.response.OrderListVO;
import com.youjia.system.youplus.global.bean.response.ProductOrderVO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 派单功能
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

    public PtProductOrder add(ProductOrderAddModel productOrderAddModel) {
        PtProductOrder ptProductOrder = new PtProductOrder();
        BeanUtil.copyProperties(productOrderAddModel, ptProductOrder);
        ptProductOrder = ptProductOrderManager.add(ptProductOrder);

        PtPrePayTemplate template = productOrderAddModel.getTemplate();
        if (template != null) {
            template.setProductId(ptProductOrder.getId());
            template = ptPrePayTemplateManager.add(template);
            ptProductOrder.setTemplateId(template.getId());
            ptProductOrderManager.update(ptProductOrder);
        }

        return ptProductOrder;
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

    public void delete(Long id) {
        ptProductOrderManager.delete(ptProductOrderManager.find(id));
    }

}
