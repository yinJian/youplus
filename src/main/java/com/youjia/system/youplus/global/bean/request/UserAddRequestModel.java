package com.youjia.system.youplus.global.bean.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author wuweifeng wrote on 2018/11/26.
 */
public class UserAddRequestModel {
    private Long id;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    @JsonIgnore
    private String password;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 手机
     */
    private String mobile;
    /**
     * 状态，（0正常，-1被停用）
     */
    private Integer state;

    private Long roleId;

    @Override
    public String toString() {
        return "UserAddRequestModel{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", state=" + state +
                ", roleId=" + roleId +
                '}';
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
