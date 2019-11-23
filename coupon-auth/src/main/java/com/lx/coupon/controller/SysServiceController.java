package com.lx.coupon.controller;

import com.lx.coupon.bean.SysUser;
import com.lx.coupon.bean.SystemUserDetails;
import com.lx.coupon.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys")
public class SysServiceController {
    @Autowired
    SysUserService sysUserService;

    @RequestMapping("info/{username}")
    public SystemUserDetails userInfo(@PathVariable("username") String username ){
        return sysUserService.queryUserAndRolesByUsername(username);
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : " + id;
    }
}
