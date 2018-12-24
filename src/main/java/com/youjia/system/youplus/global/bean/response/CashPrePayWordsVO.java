package com.youjia.system.youplus.global.bean.response;

/**
 * @author wuweifeng wrote on 2018/12/24.
 */
public class CashPrePayWordsVO {
    private String remark;
    private String filePaths;

    @Override
    public String toString() {
        return "CashPrePayWordsVO{" +
                "remark='" + remark + '\'' +
                ", filePaths='" + filePaths + '\'' +
                '}';
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFilePaths() {
        return filePaths;
    }

    public void setFilePaths(String filePaths) {
        this.filePaths = filePaths;
    }
}
