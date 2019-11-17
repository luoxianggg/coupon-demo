package com.lx.coupon.filter;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

public class AccessLogFilter extends  AbstractPostFilter {
    @Override
    protected Object cRun() {
        HttpServletRequest request = context.getRequest();
        //从 PreTokenRequestFilter 中获取请求的时间戳
        Long startTime = (Long) context.get("startTime");
        String uri = request.getRequestURI();
        Long duration = System.currentTimeMillis() - startTime;
        //从网关通过的请求都会打印日志记录：uri + durotion
        log.info("uri:{},duration:{}",uri,duration);
        return success();
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_RESPONSE_FILTER_ORDER;
    }
}
