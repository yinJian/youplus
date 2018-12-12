package com.youjia.system.youplus.global.bean.response;

import com.youjia.system.youplus.core.product.template.prepay.PtPrePayTemplate;

/**
 * @author wuweifeng wrote on 2018/12/10.
 */
public class ProductOrderVO {
    private Long productId;
    private String productName;
    
    private OrderListVO orderListVO;

    private PtPrePayTemplate template;

    private GroundPersonListVO groundPersonListVO;

    @Override
    public String toString() {
        return "ProductOrderVO{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", orderListVO=" + orderListVO +
                ", template=" + template +
                ", groundPersonListVO=" + groundPersonListVO +
                '}';
    }

    public GroundPersonListVO getGroundPersonListVO() {
        return groundPersonListVO;
    }

    public void setGroundPersonListVO(GroundPersonListVO groundPersonListVO) {
        this.groundPersonListVO = groundPersonListVO;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public OrderListVO getOrderListVO() {
        return orderListVO;
    }

    public void setOrderListVO(OrderListVO orderListVO) {
        this.orderListVO = orderListVO;
    }

    public PtPrePayTemplate getTemplate() {
        return template;
    }

    public void setTemplate(PtPrePayTemplate template) {
        this.template = template;
    }
}
