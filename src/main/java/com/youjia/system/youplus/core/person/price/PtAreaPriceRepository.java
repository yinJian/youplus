package com.youjia.system.youplus.core.person.price;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtAreaPriceRepository extends JpaRepository<PtAreaPrice, Long>,
        JpaSpecificationExecutor<PtAreaPrice> {
    PtAreaPrice findByAreaName(String areaName);
}
