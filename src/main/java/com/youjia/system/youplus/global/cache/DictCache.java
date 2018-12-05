package com.youjia.system.youplus.global.cache;

import com.youjia.system.youplus.core.dict.PtDict;
import com.youjia.system.youplus.core.dict.PtDictManager;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wuweifeng wrote on 2018/12/4.
 */
@Component
public class DictCache {
    @Resource
    private PtDictManager ptDictManager;

    private Map<Integer, Map<String, String>> dictMap = new HashMap<>();

    private Map<Integer, Map<String, String>> dict() {
        List<PtDict> list = ptDictManager.findAll();
        for (PtDict entity : list) {
            Integer groupId = entity.getGroupId();
            if (!dictMap.keySet().contains(groupId)) {
                Map<String, String> map = new HashMap<>();
                dictMap.put(groupId, map);
            }
            dictMap.get(groupId).put(entity.getdKey(), entity.getdValue());
        }
        return dictMap;
    }

    public Map<String, String> findByGroupId(Integer groupId) {
        if (dictMap.isEmpty()) {
            dict();
        }
        return dictMap.get(groupId);
    }

    public String findByGroupIdAndKey(Integer groupId, String key) {
        if (StringUtils.isEmpty(key)) {
            return "";
        }
        return findByGroupId(groupId).get(key);
    }
}
