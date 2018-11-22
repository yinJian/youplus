package com.youjia.system.youplus.core.base;

import javax.persistence.MappedSuperclass;

/**
 * @author wuweifeng wrote on 2018/11/12.
 */
@MappedSuperclass
public class BaseDeleteEntity extends BaseEntity {
    private boolean deleteFlag = true;

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
