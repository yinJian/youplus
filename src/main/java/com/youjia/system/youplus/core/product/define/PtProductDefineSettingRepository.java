package com.youjia.system.youplus.core.product.define;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtProductDefineSettingRepository extends JpaRepository<PtProductDefineSetting, Long>,
        JpaSpecificationExecutor<PtProductDefineSetting> {
    PtProductDefineSetting findFirstByProductId(Long productId);
}
