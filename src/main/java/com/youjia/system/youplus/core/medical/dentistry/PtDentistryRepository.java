package com.youjia.system.youplus.core.medical.dentistry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtDentistryRepository extends JpaRepository<PtDentistry, Long>, JpaSpecificationExecutor<PtDentistry> {
}
