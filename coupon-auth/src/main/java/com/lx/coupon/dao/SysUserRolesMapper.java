package com.lx.coupon.dao;

import com.lx.coupon.bean.SysUserRole;
import com.lx.coupon.bean.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserRolesMapper {
   SysUser queryUserByUserName(String suername);

   List<SysUserRole> queryUserRolesByUserName(String username);
}
