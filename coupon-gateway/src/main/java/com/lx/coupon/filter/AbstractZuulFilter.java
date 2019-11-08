package com.lx.coupon.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.ZuulFilterResult;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
* 一个通用的抽象过滤器类
* */
public abstract class AbstractZuulFilter extends ZuulFilter {
//用于过滤器之间的传递消息，数据保存在每个 ThreadLocal 中
    RequestContext context;
    private  final static  String NEXT = "next";

    Logger log = LoggerFactory.getLogger(AbstractZuulFilter.class);

    @Override
    public boolean shouldFilter() {
       RequestContext ctx = RequestContext.getCurrentContext();

        return (boolean) ctx.getOrDefault(NEXT, true);
    }

    @Override
    public Object run() throws ZuulException {
        context = RequestContext.getCurrentContext();
        return cRun();
    }

    protected  abstract  Object cRun();

    Object fail(int code,String msg){
        context.set(NEXT,false);
        context.setSendZuulResponse(false);
        context.getResponse().setContentType("text/html;charset=UTF-8");
        context.setResponseBody(String.format("{\"result\": \"%s!\"}",msg));
     return null;
    }
    Object success(){

        context.set(NEXT,false);
        return null;
    }
}
