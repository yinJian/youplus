package com.youjia.system.youplus.core.company.youserver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtCashPrePayRepository extends JpaRepository<PtCashPrePay, Long>,
        JpaSpecificationExecutor<PtCashPrePay> {

}
