package com.youjia.system.youplus.global.bean.response;

import com.youjia.system.youplus.core.company.goods.PtGoods;
import com.youjia.system.youplus.core.company.goods.PtGoodsTemp;

/**
 * 查看修改详情时返回的detail
 * @author wuweifeng wrote on 2018/11/15.
 */
public class GoodsModifyDetailVO {
    private PtGoods orignal;
    /**
     * 修改过的
     */
    private PtGoodsTemp modified;
    /**
     * 修改人
     */
    private String operatorName;

    @Override
    public String toString() {
        return "GoodsModifyDetailVO{" +
                "orignal=" + orignal +
                ", modified=" + modified +
                ", operatorName='" + operatorName + '\'' +
                '}';
    }

    public PtGoods getOrignal() {
        return orignal;
    }

    public void setOrignal(PtGoods orignal) {
        this.orignal = orignal;
    }

    public PtGoodsTemp getModified() {
        return modified;
    }

    public void setModified(PtGoodsTemp modified) {
        this.modified = modified;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
