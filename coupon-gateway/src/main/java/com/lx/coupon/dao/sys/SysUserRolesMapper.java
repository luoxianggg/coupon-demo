package com.lx.coupon.dao.sys;

import com.lx.coupon.bean.SysUser;

import java.util.Map;

public interface SysUserRolesMapper {
   SysUser queryUserBuUserName(Map<String,Object> map);
}
