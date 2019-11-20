package com.lx.coupon.service;

import com.lx.coupon.bean.SysUser;
import com.lx.coupon.bean.SysUserRole;
import com.lx.coupon.bean.SystemUserDetails;

import java.util.List;

public interface SysUserService {
    SysUser queryUserByUsername(String useruame);
    List<SysUserRole> queryRolesByUsername(String username);
    SystemUserDetails queryUserAndRolesByUsername(String username);


}
