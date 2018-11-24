package com.youjia.system.youplus.core.medical.doctor;

import com.xiaoleilu.hutool.util.BeanUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import com.youjia.system.youplus.core.medical.hospital.PtHospital;
import com.youjia.system.youplus.core.medical.hospital.PtHospitalManager;
import com.youjia.system.youplus.global.bean.SimplePage;
import com.youjia.system.youplus.global.bean.request.DoctorListQueryModel;
import com.youjia.system.youplus.global.bean.response.DoctorListVO;
import com.youjia.system.youplus.global.specify.Criteria;
import com.youjia.system.youplus.global.specify.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoctorService {
    @Resource
    private PtDoctorManager ptDoctorManager;
    @Resource
    private PtHospitalManager ptHospitalManager;

    public PtDoctor add(PtDoctor ptDoctor) {
        return ptDoctorManager.add(ptDoctor);
    }

    public PtDoctor update(PtDoctor ptDoctor) {
        return ptDoctorManager.add(ptDoctor);
    }

    public PtDoctor findOne(Long id) {
        return ptDoctorManager.find(id);
    }

    public DoctorListVO find(Long id) {
        return parse(findOne(id));
    }

    public void delete(Long id) {
        ptDoctorManager.delete(ptDoctorManager.find(id));
    }

    public SimplePage<DoctorListVO> find(DoctorListQueryModel doctorListQueryModel) {
        Criteria<PtDoctor> criteria = new Criteria<>();
        criteria.add(Restrictions.like("name", doctorListQueryModel.getName(), true));
        criteria.add(Restrictions.eq("level", doctorListQueryModel.getLevel(), true));
        criteria.add(Restrictions.like("phone", doctorListQueryModel.getPhone(), true));
        criteria.add(Restrictions.eq("dept1", doctorListQueryModel.getDept1(), true));
        criteria.add(Restrictions.eq("dept2", doctorListQueryModel.getDept2(), true));
        criteria.add(Restrictions.eq("province", doctorListQueryModel.getProvince(), true));
        criteria.add(Restrictions.eq("city", doctorListQueryModel.getCity(), true));
        criteria.add(Restrictions.eq("country", doctorListQueryModel.getCountry(), true));
        criteria.add(Restrictions.eq("deleteFlag", false, true));
        if (StrUtil.isNotEmpty(doctorListQueryModel.getHospitalName())) {
            List<PtHospital> hospitals = ptHospitalManager.findByNameLike(doctorListQueryModel.getHospitalName());
            if (CollectionUtil.isNotEmpty(hospitals)) {
                criteria.add(Restrictions.in("hospitalId",
                        hospitals.stream().map(PtHospital::getId).collect(Collectors.toList()), true));
            }
        }

        Pageable pageable = PageRequest.of(doctorListQueryModel.getPage(), doctorListQueryModel.getSize(),
                Sort.Direction.DESC, "id");
        Page<PtDoctor> page = ptDoctorManager.findAll(criteria, pageable);

        return new SimplePage<>(page.getTotalPages(), page.getTotalElements(), page.getContent().stream().map
                (this::parse).collect(Collectors.toList()));
    }

    private DoctorListVO parse(PtDoctor ptDoctor) {
        DoctorListVO doctorListVO = new DoctorListVO();
        BeanUtil.copyProperties(ptDoctor, doctorListVO);
        doctorListVO.setHospitalName(ptHospitalManager.findName(ptDoctor.getHospitalId()));
        return doctorListVO;
    }
}
