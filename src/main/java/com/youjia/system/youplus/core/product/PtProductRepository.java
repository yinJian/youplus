package com.youjia.system.youplus.core.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtProductRepository extends JpaRepository<PtProduct, Long>,
        JpaSpecificationExecutor<PtProduct> {
     List<PtProduct> findByDeleteFlagFalse();
}
