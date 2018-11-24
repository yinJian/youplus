package com.youjia.system.youplus.core.medical.physical;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtPhysicalRepository extends JpaRepository<PtPhysical, Long>, JpaSpecificationExecutor<PtPhysical> {
}
