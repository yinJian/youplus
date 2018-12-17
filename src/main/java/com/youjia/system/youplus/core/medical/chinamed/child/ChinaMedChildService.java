package com.youjia.system.youplus.core.medical.chinamed.child;

import com.youjia.system.youplus.global.bean.SimplePage;
import com.youjia.system.youplus.global.bean.request.ChinaMedChildListQueryModel;
import com.youjia.system.youplus.global.specify.Criteria;
import com.youjia.system.youplus.global.specify.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ChinaMedChildService {
    @Resource
    private PtChinaMedChildManager ptChinaMedChildManager;

    public PtChinaMedChild add(PtChinaMedChild ptChinaMedChild) {
        return ptChinaMedChildManager.add(ptChinaMedChild);
    }

    public PtChinaMedChild update(PtChinaMedChild ptChinaMedChild) {
        return ptChinaMedChildManager.add(ptChinaMedChild);
    }

    public PtChinaMedChild find(Long id) {
        return ptChinaMedChildManager.find(id);
    }

    public void delete(Long id) {
        ptChinaMedChildManager.delete(ptChinaMedChildManager.find(id));
    }

    /**
     * 总院被删，则删所有子院
     */
    public void deleteByChinaMedId(Long chinaMedId) {
        Criteria<PtChinaMedChild> criteria = new Criteria<>();
        criteria.add(Restrictions.eq("chinaMedId", chinaMedId, true));
        criteria.add(Restrictions.eq("deleteFlag", false, true));
        List<PtChinaMedChild> list = ptChinaMedChildManager.findAll(criteria);
        for (PtChinaMedChild ptChinaMedChild : list) {
            delete(ptChinaMedChild.getId());
        }
    }

    public List<PtChinaMedChild> findByNameLike(String name) {
        Criteria<PtChinaMedChild> criteria = new Criteria<>();
        criteria.add(Restrictions.like("name", name, true));
        criteria.add(Restrictions.eq("deleteFlag", false, true));
        return ptChinaMedChildManager.findAll(criteria);
    }

    public SimplePage<PtChinaMedChild> find(ChinaMedChildListQueryModel chinaMedChildListQueryModel) {
        Criteria<PtChinaMedChild> criteria = new Criteria<>();
        criteria.add(Restrictions.eq("chinaMedId", chinaMedChildListQueryModel.getChinaMedId(), true));
        criteria.add(Restrictions.eq("province", chinaMedChildListQueryModel.getProvince(), true));
        criteria.add(Restrictions.eq("city", chinaMedChildListQueryModel.getCity(), true));
        criteria.add(Restrictions.eq("country", chinaMedChildListQueryModel.getCountry(), true));
        criteria.add(Restrictions.like("name", chinaMedChildListQueryModel.getName(), true));
        //criteria.add(Restrictions.eq("deleteFlag", false, true));

        Pageable pageable = PageRequest.of(chinaMedChildListQueryModel.getPage(),
                chinaMedChildListQueryModel.getSize(), Sort.Direction.DESC, "id");
        Page<PtChinaMedChild> page = ptChinaMedChildManager.findAll(criteria, pageable);

        return new SimplePage<>(page.getTotalPages(), page.getTotalElements(), page.getContent());
    }

}
