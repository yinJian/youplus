package com.youjia.system.youplus.core.medical.physical;

import com.youjia.system.youplus.global.bean.SimplePage;
import com.youjia.system.youplus.global.bean.response.PhysicalListVO;
import com.youjia.system.youplus.global.specify.Criteria;
import com.youjia.system.youplus.global.specify.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PhysicalService {
    @Resource
    private PtPhysicalManager ptPhysicalManager;

    public PtPhysical add(PtPhysical ptPhysical) {
        return ptPhysicalManager.add(ptPhysical);
    }

    public PtPhysical update(PtPhysical ptPhysical) {
        return ptPhysicalManager.add(ptPhysical);
    }


    public PtPhysical find(Long id) {
        return ptPhysicalManager.find(id);
    }

    public void delete(Long id) {
        ptPhysicalManager.delete(ptPhysicalManager.find(id));
        //TODO
    }

    public SimplePage<PhysicalListVO> find(PhysicalListQueryModel PhysicalListQueryModel) {
        Criteria<PtPhysical> criteria = new Criteria<>();
        criteria.add(Restrictions.like("name", PhysicalListQueryModel.getName(), true));
        criteria.add(Restrictions.eq("country", PhysicalListQueryModel.getCountry(), true));
        criteria.add(Restrictions.eq("deleteFlag", false, true));

        Pageable pageable = PageRequest.of(PhysicalListQueryModel.getPage(),
                PhysicalListQueryModel.getSize(), Sort.Direction.DESC, "id");
        Page<PtPhysical> page = ptPhysicalManager.findAll(criteria, pageable);

        return new SimplePage<>(page.getTotalPages(), page.getTotalElements(), page.getContent());
    }

}
