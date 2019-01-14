package com.youjia.system.youplus.core.medical.dentistry.child;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtDentistryChildRepository extends JpaRepository<PtDentistryChild, Long>,
        JpaSpecificationExecutor<PtDentistryChild> {
    Long countByDentistryId(Long id);
}
