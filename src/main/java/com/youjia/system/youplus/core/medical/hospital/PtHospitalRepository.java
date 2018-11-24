package com.youjia.system.youplus.core.medical.hospital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtHospitalRepository extends JpaRepository<PtHospital, Long>, JpaSpecificationExecutor<PtHospital> {
    List<PtHospital> findByNameLikeOrOtherNameLike(String name, String otherName);
}
