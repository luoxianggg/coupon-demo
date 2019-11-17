package com.lx.coupon.service.impl;

import com.lx.coupon.bean.SysUserRole;
import com.lx.coupon.dao.SysUserRolesMapper;
import com.lx.coupon.bean.SysUser;
import com.lx.coupon.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysUserRolesMapper sysUserRolesMapper;
    @Override
    public SysUser queryUserByUsername(String useruame){
        SysUser sysUser = sysUserRolesMapper.queryUserByUserName(useruame);
        return sysUser;
    }

    @Override
    public List<SysUserRole> queryRolesByUsername(String username){
       return sysUserRolesMapper.queryUserRolesByUserName(username);
    }

}
