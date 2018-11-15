package com.youjia.system.youplus.core.company.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtCompanyTempRepository extends JpaRepository<PtCompanyTemp, Long>,
        JpaSpecificationExecutor<PtCompanyTemp> {

    PtCompanyTemp findFirstByCompanyIdOrderByIdDesc(Long companyId);
}
