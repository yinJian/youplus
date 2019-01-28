package com.youjia.system.youplus.core.product.receive;

import com.youjia.system.youplus.core.product.PtProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtOrderReceiveRepository extends JpaRepository<PtOrderReceive, Long>,
        JpaSpecificationExecutor<PtOrderReceive> {
}
