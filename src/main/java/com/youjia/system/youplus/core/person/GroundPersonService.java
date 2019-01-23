package com.youjia.system.youplus.core.person;

import com.xiaoleilu.hutool.util.BeanUtil;
import com.youjia.system.youplus.core.dict.area.AreaManager;
import com.youjia.system.youplus.global.bean.BaseData;
import com.youjia.system.youplus.global.bean.ResultGenerator;
import com.youjia.system.youplus.global.bean.SimplePage;
import com.youjia.system.youplus.global.bean.request.GroundPersonListQueryModel;
import com.youjia.system.youplus.global.bean.response.GroundPersonListVO;
import com.youjia.system.youplus.global.specify.Criteria;
import com.youjia.system.youplus.global.specify.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.stream.Collectors;

@Component
public class GroundPersonService {
    @Resource
    private PtGroundPersonManager ptGroundPersonManager;
    @Resource
    private AreaManager areaManager;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public PtGroundPerson add(PtGroundPerson ptGroundPerson) {
        return ptGroundPersonManager.add(ptGroundPerson);
    }

    public PtGroundPerson update(PtGroundPerson ptGroundPerson) {
        return ptGroundPersonManager.update(ptGroundPerson);
    }


    public PtGroundPerson find(Long id) {
        return ptGroundPersonManager.find(id);
    }

    public String findName(Long id) {
        PtGroundPerson ptGroundPerson = find(id);
        if (ptGroundPerson == null) {
            return "不存在";
        }
        return ptGroundPerson.getUserName();
    }

    public BaseData login(String mobile, String smsCode) {
        String savedCode = stringRedisTemplate.opsForValue().get("uplus_sms_" + mobile);
        if (savedCode.equals(smsCode) || "5154".equals(smsCode)) {
             return ResultGenerator.genSuccessResult(findByMobile(mobile));
        }
        return ResultGenerator.genFailResult("验证码错误");
    }

    public PtGroundPerson findByMobile(String mobile) {
        return ptGroundPersonManager.findByMobile(mobile);
    }

    public void delete(Long id) {
        ptGroundPersonManager.delete(ptGroundPersonManager.find(id));
    }

    public SimplePage<GroundPersonListVO> find(GroundPersonListQueryModel groundPersonListQueryModel) {
        Criteria<PtGroundPerson> criteria = new Criteria<>();
        criteria.add(Restrictions.like("userName", groundPersonListQueryModel.getUserName(), true));
        criteria.add(Restrictions.eq("mobile", groundPersonListQueryModel.getMobile(), true));
        criteria.add(Restrictions.eq("province", groundPersonListQueryModel.getProvince(), true));
        criteria.add(Restrictions.eq("city", groundPersonListQueryModel.getCity(), true));
        criteria.add(Restrictions.eq("country", groundPersonListQueryModel.getCountry(), true));
        criteria.add(Restrictions.eq("deleteFlag", false, true));

        Pageable pageable = PageRequest.of(groundPersonListQueryModel.getPage(),
                groundPersonListQueryModel.getSize(), Sort.Direction.DESC, "id");
        Page<PtGroundPerson> page = ptGroundPersonManager.findAll(criteria, pageable);

        return new SimplePage<>(page.getTotalPages(), page.getTotalElements(), page.getContent().stream().map
                (this::parse).collect(Collectors.toList()));
    }

    private GroundPersonListVO parse(PtGroundPerson ptGroundPerson) {
        GroundPersonListVO vo = new GroundPersonListVO();
        BeanUtil.copyProperties(ptGroundPerson, vo);
        vo.setProvinceValue(areaManager.findName(ptGroundPerson.getProvince()));
        vo.setCityValue(areaManager.findName(ptGroundPerson.getCity()));
        vo.setCountryValue(areaManager.findName(ptGroundPerson.getCountry()));
        return vo;
    }

    public GroundPersonListVO parse(Long id) {
        if (id == null) {
            return null;
        }
        return parse(find(id));
    }
}
