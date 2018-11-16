package com.youjia.system.youplus.core.company.company;

import com.xiaoleilu.hutool.util.BeanUtil;
import com.youjia.system.youplus.core.user.user.PtUserManager;
import com.youjia.system.youplus.global.bean.SimplePage;
import com.youjia.system.youplus.global.bean.request.CompanyConfirmListQueryModel;
import com.youjia.system.youplus.global.bean.request.CompanyListQueryModel;
import com.youjia.system.youplus.global.bean.response.CompanyListVO;
import com.youjia.system.youplus.global.bean.response.CompanyModifyDetailVO;
import com.youjia.system.youplus.global.bean.response.CompanyTempListVO;
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
    @Resource
    private PtUserManager ptUserManager;

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

    /**
     * 查已通过的
     */
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
     * 查待确认的，所有的
     */
    public SimplePage<CompanyTempListVO> find(CompanyConfirmListQueryModel companyConfirmListQueryModel) {
        Criteria<PtCompanyTemp> criteria = new Criteria<>();
        criteria.add(Restrictions.like("name", companyConfirmListQueryModel.getName(), true));
        criteria.add(Restrictions.eq("status", companyConfirmListQueryModel.getStatus(), true));
        criteria.add(Restrictions.eq("operatorId", companyConfirmListQueryModel.getOperatorId(), true));

        Pageable pageable = PageRequest.of(companyConfirmListQueryModel.getPage(), companyConfirmListQueryModel.getSize());
        Page<PtCompanyTemp> page = ptCompanyManager.findAllTemp(criteria, pageable);

        return new SimplePage<>(page.getTotalPages(), page.getTotalElements(), page.getContent().stream().map
                (this::parseTemp).collect(Collectors.toList()));
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

    /**
     * 包含被修改的详情
     */
    public CompanyModifyDetailVO findDetail(Long id) {
        PtCompanyTemp companyTemp = ptCompanyManager.findOneTemp(id);
        PtCompany company = ptCompanyManager.findOne(companyTemp.getCompanyId());

        CompanyModifyDetailVO companyModifyDetailVO = new CompanyModifyDetailVO();
        companyModifyDetailVO.setOperatorName(ptUserManager.findNameById(companyTemp.getOperatorId()));
        companyModifyDetailVO.setOrignal(company);
        companyModifyDetailVO.setModified(companyTemp);

        return companyModifyDetailVO;
    }

    /**
     * 审核公司，是否同意
     * @param id tempId
     * @param confirm
     * confirm
     */
    @Transactional(rollbackFor = Exception.class)
    public void confirm(Long id, Boolean confirm) {
        PtCompanyTemp companyTemp = ptCompanyManager.findOneTemp(id);
        PtCompany ptCompany = ptCompanyManager.findOne(companyTemp.getCompanyId());
        if (confirm) {
            companyTemp.setStatus(Constant.STATE_NORMAL);
            ptCompany.setStatus(Constant.STATE_NORMAL);
        } else {
            companyTemp.setStatus(Constant.STATE_REFUSE);
            ptCompany.setStatus(Constant.STATE_REFUSE);
        }
        ptCompanyManager.update(ptCompany);
        ptCompanyManager.updateTemp(companyTemp);
    }

    private CompanyListVO parse(PtCompany ptCompany) {
        CompanyListVO companyListVO = new CompanyListVO();
        BeanUtil.copyProperties(ptCompany, companyListVO);
        return companyListVO;
    }

    private CompanyTempListVO parseTemp(PtCompanyTemp ptCompany) {
        CompanyTempListVO companyListVO = new CompanyTempListVO();
        BeanUtil.copyProperties(ptCompany, companyListVO);
        companyListVO.setOperatorName(ptUserManager.findNameById(ptCompany.getOperatorId()));
        return companyListVO;
    }
}
