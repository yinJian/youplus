package com.youjia.system.youplus.core.medical.chinamed.child;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtChinaMedChildRepository extends JpaRepository<PtChinaMedChild, Long>,
        JpaSpecificationExecutor<PtChinaMedChild> {
    Long countByChinaMedId(Long id);
}
