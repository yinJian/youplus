package com.youjia.system.youplus.core.product.template.prepay;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtPrePayTemplateRepository extends JpaRepository<PtPrePayTemplate, Long>,
        JpaSpecificationExecutor<PtPrePayTemplate> {
}
