package com.lx.coupon.bean;

import lombok.Data;

@Data
public class SysUserRole {
    private Integer roleId;
    private String roleName;
    private String roleCode;
    private char enableFlag;
}
