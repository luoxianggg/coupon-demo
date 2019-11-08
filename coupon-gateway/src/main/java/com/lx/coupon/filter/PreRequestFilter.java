package com.lx.coupon.filter;

/*
* 在过滤器中存储发起请求的开始时间戳
* */
public class PreRequestFilter extends  AbstractPreFilter {
    @Override
    protected Object cRun() {
        context.set("startTime",System.currentTimeMillis());
        return null;
    }

    @Override
    public int filterOrder() {
       //优先级最高
        return 0;
    }
}
