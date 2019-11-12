package com.lx.coupon.service.sys;

import com.lx.coupon.bean.SysUser;

public interface SysUserService {
    SysUser login(String username, String password);

    boolean userExistInCache(String token);
}
