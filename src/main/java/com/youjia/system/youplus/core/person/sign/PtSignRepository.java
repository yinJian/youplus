package com.youjia.system.youplus.core.person.sign;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtSignRepository extends JpaRepository<PtSign, Long>,
        JpaSpecificationExecutor<PtSign> {
    PtSign findByGroundPersonId(Long id);
}
