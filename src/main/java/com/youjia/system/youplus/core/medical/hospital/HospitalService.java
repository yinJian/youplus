package com.youjia.system.youplus.core.medical.hospital;

import com.youjia.system.youplus.global.bean.SimplePage;
import com.youjia.system.youplus.global.bean.request.HospitalListQueryModel;
import com.youjia.system.youplus.global.specify.Criteria;
import com.youjia.system.youplus.global.specify.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class HospitalService {
    @Resource
    private PtHospitalManager ptHospitalManager;

    public PtHospital add(PtHospital ptHospital) {
        return ptHospitalManager.add(ptHospital);
    }

    public PtHospital update(PtHospital ptHospital) {
        return ptHospitalManager.add(ptHospital);
    }


    public PtHospital find(Long id) {
        return ptHospitalManager.find(id);
    }

    public void delete(Long id) {
        ptHospitalManager.delete(ptHospitalManager.find(id));
    }

    public SimplePage<PtHospital> find(HospitalListQueryModel hospitalListQueryModel) {
        Criteria<PtHospital> criteria = new Criteria<>();
        criteria.add(Restrictions.like("name", hospitalListQueryModel.getName(), true));
        criteria.add(Restrictions.eq("level", hospitalListQueryModel.getLevel(), true));
        criteria.add(Restrictions.eq("province", hospitalListQueryModel.getProvince(), true));
        criteria.add(Restrictions.eq("city", hospitalListQueryModel.getCity(), true));
        criteria.add(Restrictions.eq("country", hospitalListQueryModel.getCountry(), true));
        criteria.add(Restrictions.eq("deleteFlag", false, true));

        Pageable pageable = PageRequest.of(hospitalListQueryModel.getPage(),
                hospitalListQueryModel.getSize(), Sort.Direction.DESC, "id");
        Page<PtHospital> page = ptHospitalManager.findAll(criteria, pageable);

        return new SimplePage<>(page.getTotalPages(), page.getTotalElements(), page.getContent());
    }

}
