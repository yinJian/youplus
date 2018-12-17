package com.youjia.system.youplus.core.product.flow;

import com.youjia.system.youplus.core.base.BaseEntity;

import javax.persistence.Entity;
import java.util.Date;

/**
 * 订单各流程阶段
 * @author wuweifeng wrote on 2018/12/3.
 */
@Entity
public class PtOrderFlow extends BaseEntity {
    private Long productOrderId;
    /**
     * 为null则是未接单。存在已派单，但未接单的情况
     */
    private Long groundPersonId;
    /**
     * 接单时间
     */
    private Date groundPersonIdTime;
    /**
     * 理赔相关信息的id
     */
    private Long claimId;
    /**
     * 生成理赔单，时间
     */
    private Date claimIdTime;
    /**
     * 与患者联系人沟通服务细节
     */
    private boolean hasContact;
    /**
     * 与患者联系人沟通服务细节，时间
     */
    private Date hasContactTime;
    /**
     * 检查并携带相关协议无误
     */
    private boolean hasChecked;
    /**
     * 检查并携带相关协议无误，时间
     */
    private Date hasCheckedTime;
    /**
     * 提交押金垫付已签署文件。逗号分隔
     */
    private String prePayFiles;
    /**
     * 是否已确认"押金垫付的文件"
     */
    private boolean prePayFileConfirm;
    /**
     * 是否已确认"押金垫付的文件"，确认时间
     */
    private Date prePayFileConfirmTime;
    /**
     * 确认垫付金额
     */
    private Double prePayMoney;
    /**
     * 是否拒绝赔付，true为拒绝，null为没设置
     */
    private Boolean refusePay;
    /**
     * 确认垫付金额，确认时间
     */
    private Date prePayMoneyTime;
    /**
     * 住院押金条
     */
    private String depositFiles;
    /**
     * 住院押金条，确认时间
     */
    private Date depositFilesTime;
    /**
     * 是否驳回押金条
     */
    private Boolean refuseDeposit;
    /**
     * 出院结算明细
     */
    private String balanceFiles;
    /**
     * 出院结算明细，确认时间
     */
    private Date balanceFilesTime;
    /**
     * 是否驳回出院结算单子
     */
    private Boolean refuseBalance;
    /**
     * 需要追缴用户欠费xxx元
     */
    private Double needMoney;
    /**
     * 追缴完成
     */
    private Boolean finishNeedMoney;
    /**
     * 追缴完成，确认时间
     */
    private Date finishNeedMoneyTime;
    /**
     * 服务完成时间
     */
    private Date finishTime;

    @Override
    public String toString() {
        return "PtOrderFlow{" +
                "productOrderId=" + productOrderId +
                ", groundPersonId=" + groundPersonId +
                ", groundPersonIdTime=" + groundPersonIdTime +
                ", claimId=" + claimId +
                ", claimIdTime=" + claimIdTime +
                ", hasContact=" + hasContact +
                ", hasContactTime=" + hasContactTime +
                ", hasChecked=" + hasChecked +
                ", hasCheckedTime=" + hasCheckedTime +
                ", prePayFiles='" + prePayFiles + '\'' +
                ", prePayFileConfirm=" + prePayFileConfirm +
                ", prePayFileConfirmTime=" + prePayFileConfirmTime +
                ", prePayMoney=" + prePayMoney +
                ", refusePay=" + refusePay +
                ", prePayMoneyTime=" + prePayMoneyTime +
                ", depositFiles='" + depositFiles + '\'' +
                ", depositFilesTime=" + depositFilesTime +
                ", refuseDeposit=" + refuseDeposit +
                ", balanceFiles='" + balanceFiles + '\'' +
                ", balanceFilesTime=" + balanceFilesTime +
                ", refuseBalance=" + refuseBalance +
                ", needMoney=" + needMoney +
                ", finishNeedMoney=" + finishNeedMoney +
                ", finishNeedMoneyTime=" + finishNeedMoneyTime +
                ", finishTime=" + finishTime +
                '}';
    }

    public Date getGroundPersonIdTime() {
        return groundPersonIdTime;
    }

    public void setGroundPersonIdTime(Date groundPersonIdTime) {
        this.groundPersonIdTime = groundPersonIdTime;
    }

    public Date getClaimIdTime() {
        return claimIdTime;
    }

    public void setClaimIdTime(Date claimIdTime) {
        this.claimIdTime = claimIdTime;
    }

    public Date getHasContactTime() {
        return hasContactTime;
    }

    public void setHasContactTime(Date hasContactTime) {
        this.hasContactTime = hasContactTime;
    }

    public Date getHasCheckedTime() {
        return hasCheckedTime;
    }

    public void setHasCheckedTime(Date hasCheckedTime) {
        this.hasCheckedTime = hasCheckedTime;
    }

    public Date getPrePayFileConfirmTime() {
        return prePayFileConfirmTime;
    }

    public void setPrePayFileConfirmTime(Date prePayFileConfirmTime) {
        this.prePayFileConfirmTime = prePayFileConfirmTime;
    }

    public Date getPrePayMoneyTime() {
        return prePayMoneyTime;
    }

    public void setPrePayMoneyTime(Date prePayMoneyTime) {
        this.prePayMoneyTime = prePayMoneyTime;
    }

    public Date getDepositFilesTime() {
        return depositFilesTime;
    }

    public void setDepositFilesTime(Date depositFilesTime) {
        this.depositFilesTime = depositFilesTime;
    }

    public Date getBalanceFilesTime() {
        return balanceFilesTime;
    }

    public void setBalanceFilesTime(Date balanceFilesTime) {
        this.balanceFilesTime = balanceFilesTime;
    }

    public Date getFinishNeedMoneyTime() {
        return finishNeedMoneyTime;
    }

    public void setFinishNeedMoneyTime(Date finishNeedMoneyTime) {
        this.finishNeedMoneyTime = finishNeedMoneyTime;
    }

    public Long getProductOrderId() {
        return productOrderId;
    }

    public void setProductOrderId(Long productOrderId) {
        this.productOrderId = productOrderId;
    }

    public Long getGroundPersonId() {
        return groundPersonId;
    }

    public void setGroundPersonId(Long groundPersonId) {
        this.groundPersonId = groundPersonId;
    }

    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public boolean isHasContact() {
        return hasContact;
    }

    public void setHasContact(boolean hasContact) {
        this.hasContact = hasContact;
    }

    public boolean isHasChecked() {
        return hasChecked;
    }

    public void setHasChecked(boolean hasChecked) {
        this.hasChecked = hasChecked;
    }

    public String getPrePayFiles() {
        return prePayFiles;
    }

    public void setPrePayFiles(String prePayFiles) {
        this.prePayFiles = prePayFiles;
    }

    public boolean isPrePayFileConfirm() {
        return prePayFileConfirm;
    }

    public void setPrePayFileConfirm(boolean prePayFileConfirm) {
        this.prePayFileConfirm = prePayFileConfirm;
    }

    public Double getPrePayMoney() {
        return prePayMoney;
    }

    public void setPrePayMoney(Double prePayMoney) {
        this.prePayMoney = prePayMoney;
    }

    public Boolean getRefusePay() {
        return refusePay;
    }

    public void setRefusePay(Boolean refusePay) {
        this.refusePay = refusePay;
    }

    public String getDepositFiles() {
        return depositFiles;
    }

    public void setDepositFiles(String depositFiles) {
        this.depositFiles = depositFiles;
    }

    public Boolean getRefuseDeposit() {
        return refuseDeposit;
    }

    public void setRefuseDeposit(Boolean refuseDeposit) {
        this.refuseDeposit = refuseDeposit;
    }

    public String getBalanceFiles() {
        return balanceFiles;
    }

    public void setBalanceFiles(String balanceFiles) {
        this.balanceFiles = balanceFiles;
    }

    public Boolean getRefuseBalance() {
        return refuseBalance;
    }

    public void setRefuseBalance(Boolean refuseBalance) {
        this.refuseBalance = refuseBalance;
    }

    public Double getNeedMoney() {
        return needMoney;
    }

    public void setNeedMoney(Double needMoney) {
        this.needMoney = needMoney;
    }

    public Boolean getFinishNeedMoney() {
        return finishNeedMoney;
    }

    public void setFinishNeedMoney(Boolean finishNeedMoney) {
        this.finishNeedMoney = finishNeedMoney;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}
