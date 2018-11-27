package com.youjia.system.youplus.core.dict.area;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface AreaRepository extends JpaRepository<AreaEntity, Long> {
    AreaEntity findByAreaCode(String areaCode);

    List<AreaEntity> findByParentId(Long parentId);
}
