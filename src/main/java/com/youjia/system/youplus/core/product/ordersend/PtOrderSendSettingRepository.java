package com.youjia.system.youplus.core.product.ordersend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtOrderSendSettingRepository extends JpaRepository<PtOrderSendSetting, Long>,
        JpaSpecificationExecutor<PtOrderSendSetting> {
    List<PtOrderSendSetting> findByProductIdOrderBySortDesc(Long productId);

    @Modifying
    @Query("delete from PtOrderSendSetting where productId = ?1")
    void deleteByProductId(Long productId);
}
