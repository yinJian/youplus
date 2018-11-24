package com.youjia.system.youplus.core.medical.physical.child;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PtPhysicalChildManager {
    @Resource
    private PtPhysicalChildRepository ptPhysicalChildRepository;

    public Page<PtPhysicalChild> findAll(Specification<PtPhysicalChild> var1, Pageable var2) {
        return ptPhysicalChildRepository.findAll(var1, var2);
    }

    public PtPhysicalChild find(Long id) {
        return ptPhysicalChildRepository.getOne(id);
    }

    public PtPhysicalChild add(PtPhysicalChild ptPhysical) {
        return save(ptPhysical);
    }

    public PtPhysicalChild update(PtPhysicalChild ptPhysical) {
        return save(ptPhysical);
    }

    public void delete(PtPhysicalChild ptPhysical) {
        ptPhysical.setDeleteFlag(true);
        update(ptPhysical);
    }

    private PtPhysicalChild save(PtPhysicalChild ptPhysical)  {
        return ptPhysicalChildRepository.save(ptPhysical);
    }

}
