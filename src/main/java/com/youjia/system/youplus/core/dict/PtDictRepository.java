package com.youjia.system.youplus.core.dict;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wuweifeng wrote on 2017/10/26.
 */
public interface PtDictRepository extends JpaRepository<PtDict, Long> {
    List<PtDict> findByGroupId(Integer groupId);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    void deleteByParentKey(String key);
}
