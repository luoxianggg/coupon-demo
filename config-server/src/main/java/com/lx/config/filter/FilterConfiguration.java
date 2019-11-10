package com.lx.config.filter;

import com.lx.config.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {
    @Autowired
    UserService userService;
    @Bean
    public FilterRegistrationBean loginFilterRegistration (){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(
                new LoginFilter(userService)
        );
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("loginFilter");
        registrationBean.setOrder(2);
        return registrationBean;
    }
}
