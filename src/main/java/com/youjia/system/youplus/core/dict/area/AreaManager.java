package com.youjia.system.youplus.core.dict.area;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/11/15.
 */
@Service
public class AreaManager {
    @Resource
    private AreaRepository areaRepository;

    /**
     * 查询完整地址
     * @param areaCode areaCode
     * @return 河南-漯河-源汇
     */
    public String findFull(String areaCode) {
        if (areaCode == null) {
            return "";
        }
        AreaEntity areaEntity = areaRepository.findByAreaCode(areaCode);
        //区
        String qu = areaEntity.getAreaName();
        AreaEntity city = areaRepository.getOne(areaEntity.getId());
        //市
        String shi = city.getAreaName();
        AreaEntity province = areaRepository.getOne(city.getParentId());
        String sheng = province.getAreaName();
        return sheng + "-" + shi + "-" + qu;
    }
}
