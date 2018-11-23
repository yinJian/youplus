package com.youjia.system.youplus.core.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtOrderTempRepository extends JpaRepository<PtOrderTemp, Long>,
        JpaSpecificationExecutor<PtOrderTemp> {
    PtOrderTemp findFirstByOrderIdOrderByIdDesc(Long orderId);
}
