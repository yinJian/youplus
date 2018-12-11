package com.youjia.system.youplus.core.product;

import com.youjia.system.youplus.core.product.define.PtProductDefineSetting;
import com.youjia.system.youplus.core.product.define.PtProductDefineSettingManager;
import com.youjia.system.youplus.core.product.ordersend.PtOrderSendSettingManager;
import com.youjia.system.youplus.global.bean.request.ProductAddUpdateModel;
import com.youjia.system.youplus.global.bean.response.ProductVO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Component
@Transactional(rollbackFor = Exception.class)
public class ProductService {
    @Resource
    private PtProductManager ptProductManager;
    @Resource
    private PtProductDefineSettingManager ptProductDefineSettingManager;
    @Resource
    private PtOrderSendSettingManager ptOrderSendSettingManager;

    public PtProduct add(ProductAddUpdateModel productAddUpdateModel) {
        PtProduct ptProduct = productAddUpdateModel.getProduct();
        if (ptProduct != null) {
            ptProduct = ptProductManager.add(ptProduct);
        }
        PtProductDefineSetting ptProductDefineSetting = productAddUpdateModel.getProductDefineSetting();
        if (ptProductDefineSetting != null) {
            ptProductDefineSetting.setProductId(ptProduct.getId());
            ptProductDefineSettingManager.add(ptProductDefineSetting);
        }
        //List<PtOrderSendSetting> ptOrderSendSettings = productAddUpdateModel.getOrderSendSettings();
        //if (CollectionUtil.isNotEmpty(ptOrderSendSettings)) {
        //    for (PtOrderSendSetting sendSetting : ptOrderSendSettings) {
        //        sendSetting.setProductId(ptProduct.getId());
        //        ptOrderSendSettingManager.add(sendSetting);
        //    }
        //}

        return ptProduct;
    }

    public PtProduct update(ProductAddUpdateModel productAddUpdateModel) {
        PtProduct ptProduct = productAddUpdateModel.getProduct();
        if (ptProduct != null) {
            ptProduct = ptProductManager.update(ptProduct);
        }
        PtProductDefineSetting ptProductDefineSetting = productAddUpdateModel.getProductDefineSetting();
        if (ptProductDefineSetting != null) {
            ptProductDefineSetting.setProductId(ptProduct.getId());
            ptProductDefineSettingManager.update(ptProductDefineSetting);
        }
        //List<PtOrderSendSetting> ptOrderSendSettings = productAddUpdateModel.getOrderSendSettings();
        //if (CollectionUtil.isNotEmpty(ptOrderSendSettings)) {
        //    ptOrderSendSettingManager.deleteByProductId(ptProduct.getId());
        //    for (PtOrderSendSetting sendSetting : ptOrderSendSettings) {
        //        sendSetting.setProductId(ptProduct.getId());
        //        ptOrderSendSettingManager.add(sendSetting);
        //    }
        //}

        return ptProduct;
    }

    public ProductVO find(Long id) {
        ProductVO productVO = new ProductVO();
        PtProduct ptProduct = ptProductManager.find(id);
        PtProductDefineSetting ptProductDefineSetting = ptProductDefineSettingManager.findByProductId(id);
        //List<PtOrderSendSetting> ptOrderSendSetting = ptOrderSendSettingManager.findByProductId(id);

        productVO.setProduct(ptProduct);
        //productVO.setOrderSendSettings(ptOrderSendSetting);
        productVO.setProductDefineSetting(ptProductDefineSetting);
        return productVO;
    }

    public void delete(Long id) {
        ptProductManager.delete(ptProductManager.find(id));
    }

    public List<PtProduct> findAll() {
        return ptProductManager.findAll();
    }

}
