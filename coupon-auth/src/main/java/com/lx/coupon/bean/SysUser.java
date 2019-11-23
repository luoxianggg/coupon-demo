package com.lx.coupon.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

//@AllArgsConstructor
//@Data
public class SysUser implements Serializable {
    private Integer userId;
    private String password;
    private String iconUrl;
    private  String userName;

   public SysUser(){}

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {

        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getUserName() {
        return userName;
    }
   /* public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastLoginDateTime() {
        return lastLoginDateTime;
    }

    public void setLastLoginDateTime(String lastLoginDateTime) {
        this.lastLoginDateTime = lastLoginDateTime;
    }

    public long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(long uniqueId) {
        this.uniqueId = uniqueId;
    }*/
}
