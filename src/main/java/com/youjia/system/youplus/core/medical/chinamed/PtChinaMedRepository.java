package com.youjia.system.youplus.core.medical.chinamed;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtChinaMedRepository extends JpaRepository<PtChinaMed, Long>, JpaSpecificationExecutor<PtChinaMed> {
}
