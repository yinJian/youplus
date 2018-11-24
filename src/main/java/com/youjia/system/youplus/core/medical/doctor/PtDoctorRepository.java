package com.youjia.system.youplus.core.medical.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtDoctorRepository extends JpaRepository<PtDoctor, Long>,
        JpaSpecificationExecutor<PtDoctor> {
}
