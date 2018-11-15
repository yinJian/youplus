package com.youjia.system.youplus.global.bean.response;

import com.youjia.system.youplus.core.company.company.PtCompany;
import com.youjia.system.youplus.core.company.company.PtCompanyTemp;

/**
 * 查看修改详情时返回的detail
 * @author wuweifeng wrote on 2018/11/15.
 */
public class CompanyModifyDetailVO {
    private PtCompany orignal;
    /**
     * 修改过的
     */
    private PtCompanyTemp modified;
    /**
     * 修改人
     */
    private String modifyName;

    @Override
    public String toString() {
        return "CompanyModifyDetailVO{" +
                "orignal=" + orignal +
                ", modified=" + modified +
                ", modifyName='" + modifyName + '\'' +
                '}';
    }

    public PtCompany getOrignal() {
        return orignal;
    }

    public void setOrignal(PtCompany orignal) {
        this.orignal = orignal;
    }

    public PtCompanyTemp getModified() {
        return modified;
    }

    public void setModified(PtCompanyTemp modified) {
        this.modified = modified;
    }

    public String getModifyName() {
        return modifyName;
    }

    public void setModifyName(String modifyName) {
        this.modifyName = modifyName;
    }
}
