package com.lx.coupon.bean;

import lombok.Data;

@Data
public class SysUser {
    private  String userName;

    private String lastLoginDateTime;

    private transient long uniqueId;

    public SysUser(String userName, String lastLoginDate , String lastLoginTime) {
        this.userName = userName;
        if(lastLoginDate==null||lastLoginTime==null){
            this.lastLoginDateTime=null;
        }else{
            this.lastLoginDateTime = lastLoginDate+" "+lastLoginTime;
        }

    }

    public SysUser() {
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
