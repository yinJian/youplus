package com.youjia.system.youplus.core.product.change;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtChangeRepository extends JpaRepository<PtChange, Long>,
        JpaSpecificationExecutor<PtChange> {
    List<PtChange> findByOldPersonIdOrNewPersonId(Long oldId, Long newId);

    PtChange findFirstByNewPersonIdOrderByIdDesc(Long newId);
}
