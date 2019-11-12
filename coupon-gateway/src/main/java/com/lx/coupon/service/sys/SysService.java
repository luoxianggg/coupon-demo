package com.lx.coupon.service.sys;

import com.lx.coupon.bean.SysUser;

public interface SysService {
    SysUser queryUserInfo(String token);

 /*   List<SCCSystem> querySysInfo();

    List<Param> queryParamInfo(String sysId, String envName);

    int updateParam(String paramId, String paramValue);*/
}
