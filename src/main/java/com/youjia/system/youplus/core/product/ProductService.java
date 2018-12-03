package com.youjia.system.youplus.core.product;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ProductService {
    @Resource
    private PtProductManager ptProductManager;

    public PtProduct add(PtProduct ptProduct) {
        return ptProductManager.add(ptProduct);
    }

    public PtProduct update(PtProduct ptProduct) {
        return ptProductManager.update(ptProduct);
    }

    public PtProduct find(Long id) {
        return ptProductManager.find(id);
    }

    public void delete(Long id) {
        ptProductManager.delete(ptProductManager.find(id));
    }

    public List<PtProduct> findAll() {
        return ptProductManager.findAll();
    }

}
