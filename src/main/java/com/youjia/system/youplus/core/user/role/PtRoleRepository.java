package com.youjia.system.youplus.core.user.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtRoleRepository extends JpaRepository<PtRole, Long> {
    List<PtRole> findByDeleteFlagFalse();
}
