package com.youjia.system.youplus.core.person.sign;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PtSignManager {
    @Resource
    private PtSignRepository ptSignRepository;

    public Page<PtSign> findAll(Specification<PtSign> var1, Pageable var2) {
        return ptSignRepository.findAll(var1, var2);
    }

    public PtSign findByGroundPersonId(Long personId) {
        return ptSignRepository.findByGroundPersonId(personId);
    }

    public PtSign find(Long id) {
        return ptSignRepository.getOne(id);
    }

    public PtSign add(PtSign ptSign) {
        return save(ptSign);
    }

    public PtSign update(PtSign ptSign) {
        return save(ptSign);
    }

    public void delete(PtSign ptSign) {
        ptSign.setDeleteFlag(true);
        update(ptSign);
    }

    private PtSign save(PtSign ptSign) {
        return ptSignRepository.save(ptSign);
    }
}
