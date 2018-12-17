package com.youjia.system.youplus.core.product.flow.claim;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtClaimRepository extends JpaRepository<PtClaim, Long>,
        JpaSpecificationExecutor<PtClaim> {
    PtClaim findFirstByProductOrderId(Long productOrderId);
}
