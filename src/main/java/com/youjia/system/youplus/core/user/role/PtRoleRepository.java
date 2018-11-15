package com.youjia.system.youplus.core.user.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtRoleRepository extends JpaRepository<PtRole, Long> {
    PtRole findByName(String name);

    /**
     * 查询某个公司的所有role
     *
     * @param companyId
     *         CompanyId
     * @return 角色集合
     */
    List<PtRole> findByCompanyId(Long companyId);

    List<PtRole> findByCompanyIdAndNameLike(Long companyId, String name);
}
