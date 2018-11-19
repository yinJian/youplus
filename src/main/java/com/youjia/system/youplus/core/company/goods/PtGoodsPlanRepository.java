package com.youjia.system.youplus.core.company.goods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtGoodsPlanRepository extends JpaRepository<PtGoodsPlan, Long>,
        JpaSpecificationExecutor<PtGoodsPlan> {
    List<PtGoodsPlan> findByDeleteFlagFalse();
}
