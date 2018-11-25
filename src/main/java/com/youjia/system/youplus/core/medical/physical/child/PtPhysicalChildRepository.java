package com.youjia.system.youplus.core.medical.physical.child;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtPhysicalChildRepository extends JpaRepository<PtPhysicalChild, Long>,
        JpaSpecificationExecutor<PtPhysicalChild> {
    Long countByDeleteFlagAndPhysicalId(boolean flag, Long id);
}
