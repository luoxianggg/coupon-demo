package com.lx.config.bean;

public class SCCSystem {
    private int sysId;

    private String sysName;

    public SCCSystem() {
    }

    public SCCSystem(int sysId, String sysName) {
        this.sysId = sysId;
        this.sysName = sysName;
    }

    public int getSysId() {
        return sysId;
    }

    public void setSysId(int sysId) {
        this.sysId = sysId;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }
}
