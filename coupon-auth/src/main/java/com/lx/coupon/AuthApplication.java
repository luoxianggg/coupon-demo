package com.lx.coupon;

import com.lx.coupon.bean.SysUser;
import com.lx.coupon.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@EnableDiscoveryClient
@SpringBootApplication
@EnableAuthorizationServer
@MapperScan(basePackages = "com.lx.coupon.dao")
public class AuthApplication {
    @Autowired
    SysUserService sysUserService;
    @RequestMapping(value = {"/user"}, produces = "application/json")
    public Map<String,Object> user(OAuth2Authentication user){
        Map<String,Object> userInfo = new HashMap<>();
        userInfo.put("user",user.getUserAuthentication().getPrincipal());
        userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
        return userInfo;
    }
    @RequestMapping(value = "/admin/registry", method = RequestMethod.POST)
    public SysUser createUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
            return sysUserService.queryUserByUsername(username);
        }

        return null;
    }
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class,args);
    }
}
