package com.lx.coupon.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/*
* JackSon 的自定义配置
* */
@Configuration
public class JacksonConfig {

    public  ObjectMapper getObjectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-mm-dd HH-mm:ss"));
     return mapper;
    }
}
