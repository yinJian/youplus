package com.youjia.system.youplus.core.product.flow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtOrderFlowRepository extends JpaRepository<PtOrderFlow, Long>,
        JpaSpecificationExecutor<PtOrderFlow> {

    PtOrderFlow findFirstByProductOrderId(Long productOrderId);
}
