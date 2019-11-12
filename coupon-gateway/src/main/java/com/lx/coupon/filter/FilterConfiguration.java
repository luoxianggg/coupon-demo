package com.lx.coupon.filter;

import com.lx.coupon.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {
    @Autowired
    SysUserService sysUserService;
    @Bean
    public FilterRegistrationBean loginFilterRegistration (){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(
                new LoginFilter(sysUserService)
        );
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("loginFilter");
        registrationBean.setOrder(2);
        return registrationBean;
    }
}
