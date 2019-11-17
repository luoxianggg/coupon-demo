package com.lx.coupon.service;

import com.lx.coupon.bean.SysUser;
import com.lx.coupon.bean.SysUserRole;

import java.util.List;

public interface SysUserService {
    SysUser queryUserByUsername(String useruame);
    List<SysUserRole> queryRolesByUsername(String username);


}
