package com.lx.config.service;

import com.lx.config.bean.Param;
import com.lx.config.bean.SCCSystem;
import com.lx.config.bean.SCCUser;

import java.util.List;

public interface SysService {
    SCCUser queryUserInfo(String token);

    List<SCCSystem> querySysInfo();

    List<Param> queryParamInfo(String sysId, String envName);

    int updateParam(String paramId,String paramValue);
}
