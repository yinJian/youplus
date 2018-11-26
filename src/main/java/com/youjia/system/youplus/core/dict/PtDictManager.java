package com.youjia.system.youplus.core.dict;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wuweifeng wrote on 2018/11/14.
 */
@Service
public class PtDictManager {
    @Resource
    private PtDictRepository ptDictRepository;

    public List<PtDict> findAll() {
        return ptDictRepository.findAll();
    }

    public PtDict add(PtDict ptDict) {
        return save(ptDict);
    }

    public PtDict update(PtDict ptDict) {
        return save(ptDict);
    }

    public void delete(PtDict ptDict) {
        ptDictRepository.delete(ptDict);
    }

    public PtDict find(Long id) {
        return ptDictRepository.getOne(id);
    }

    public PtDict save(PtDict ptDict) {
        return ptDictRepository.save(ptDict);
    }
}
