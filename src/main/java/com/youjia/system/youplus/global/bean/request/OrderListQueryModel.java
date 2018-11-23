package com.youjia.system.youplus.global.bean.request;

/**
 * @author wuweifeng wrote on 2018/11/22.
 */
public class OrderListQueryModel extends BaseModel {
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 保单号
     */
    private String cardNum;
    /**
     * 电话号码
     */
    private String mobile;
    /**
     * 证件号码
     */
    private String paper;
    /**
     * 公司ID
     */
    private Long companyId;
    /**
     * 操作人ID
     */
    private Long operatorId;
    /**
     * -1待审核，-2被拒绝，0正常
     */
    private Integer status;

    @Override
    public String toString() {
        return "OrderListQueryModel{" +
                "userName='" + userName + '\'' +
                ", cardNum='" + cardNum + '\'' +
                ", mobile='" + mobile + '\'' +
                ", paper='" + paper + '\'' +
                ", companyId=" + companyId +
                ", operatorId=" + operatorId +
                ", status=" + status +
                '}';
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPaper() {
        return paper;
    }

    public void setPaper(String paper) {
        this.paper = paper;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
