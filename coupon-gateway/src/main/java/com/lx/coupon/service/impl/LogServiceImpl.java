package com.lx.coupon.service.impl;

import com.lx.coupon.service.sys.LogService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LogServiceImpl implements LogService {
    @Override
    public  void log(Map<String,Object> map){
        //sysBaseInfo.insertLog(map);
    }
}
