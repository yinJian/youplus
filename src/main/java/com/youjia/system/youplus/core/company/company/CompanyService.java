package com.youjia.system.youplus.core.company.company;

import com.xiaoleilu.hutool.util.BeanUtil;
import com.youjia.system.youplus.global.bean.SimplePage;
import com.youjia.system.youplus.global.bean.request.CompanyListQueryModel;
import com.youjia.system.youplus.global.bean.response.CompanyListVO;
import com.youjia.system.youplus.global.specify.Criteria;
import com.youjia.system.youplus.global.specify.Restrictions;
import com.youjia.system.youplus.global.util.Constant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * @author wuweifeng wrote on 2018/11/15.
 */
@Component
public class CompanyService {
    @Resource
    private PtCompanyManager ptCompanyManager;

    @Transactional(rollbackFor = Exception.class)
    public PtCompany add(PtCompany ptCompany) {
        ptCompany.setStatus(Constant.STATE_CONFIRM);
        ptCompany = ptCompanyManager.add(ptCompany);

        //同时新建一个temp
        PtCompanyTemp ptCompanyTemp = new PtCompanyTemp();
        BeanUtil.copyProperties(ptCompany, ptCompanyTemp, "id");
        ptCompanyTemp.setReason(Constant.REASON_NEW_CREATE);
        ptCompanyTemp.setCompanyId(ptCompany.getId());
        ptCompanyManager.save(ptCompanyTemp);
        return ptCompany;
    }

    @Transactional(rollbackFor = Exception.class)
    public PtCompany update(PtCompany ptCompany, String reason) {
        //待审核
        ptCompany.setStatus(Constant.STATE_CONFIRM);
        ptCompany = ptCompanyManager.update(ptCompany);

        //看有没有在修改中的，有则取，无则新建
        PtCompanyTemp ptCompanyTemp = ptCompanyManager.findTempByCompanyId(ptCompany.getId());
        if (ptCompanyTemp == null) {
            ptCompanyTemp = new PtCompanyTemp();
            ptCompanyTemp.setCompanyId(ptCompany.getId());
        }
        ptCompanyTemp.setReason(reason);
        BeanUtil.copyProperties(ptCompany, ptCompanyTemp, "id");
        ptCompanyManager.save(ptCompanyTemp);

        return ptCompany;
    }

    public SimplePage<CompanyListVO> find(CompanyListQueryModel companyListQueryModel) {
        Criteria<PtCompany> criteria = new Criteria<>();
        criteria.add(Restrictions.like("name", companyListQueryModel.getName(), true));
        criteria.add(Restrictions.eq("type", companyListQueryModel.getType(), true));
        criteria.add(Restrictions.eq("status", Constant.STATE_NORMAL, true));

        Pageable pageable = PageRequest.of(companyListQueryModel.getPage(), companyListQueryModel.getSize());
        Page<PtCompany> page = ptCompanyManager.findAll(criteria, pageable);

        return new SimplePage<>(page.getTotalPages(), page.getTotalElements(), page.getContent().stream().map
                (this::parse).collect(Collectors.toList()));
    }

    /**
     * 查询某公司详情
     *
     * @param id
     *         id
     * @return PtCompany
     */
    public PtCompany findOne(Long id) {
        return ptCompanyManager.findOne(id);
    }

    private CompanyListVO parse(PtCompany ptCompany) {
        CompanyListVO companyListVO = new CompanyListVO();
        BeanUtil.copyProperties(ptCompany, companyListVO);
        return companyListVO;
    }
}
