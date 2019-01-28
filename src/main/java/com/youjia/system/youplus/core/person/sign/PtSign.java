package com.youjia.system.youplus.core.person.sign;


import com.youjia.system.youplus.core.base.BaseDeleteEntity;

import javax.persistence.Entity;

/**
 * @author wuweifeng wrote on 2017/10/25.
 * 签约
 */
@Entity
public class PtSign extends BaseDeleteEntity {

    /**
     * 地勤id
     */
    private Long groundPersonId;
    /**
     * 合同地址
     */
    private String path;

    @Override
    public String toString() {
        return "PtSign{" +
                "groundPersonId=" + groundPersonId +
                ", path='" + path + '\'' +
                '}';
    }

    public Long getGroundPersonId() {
        return groundPersonId;
    }

    public void setGroundPersonId(Long groundPersonId) {
        this.groundPersonId = groundPersonId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
