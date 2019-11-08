package com.lx.coupon.filter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/*
* 限流过滤器
*
* */
@Slf4j
@Component
@SuppressWarnings("all")
public class RateLimitFilter extends  AbstractPreFilter {
   /*每秒可以获取到两个令牌*/
    RateLimiter rateLimiter = RateLimiter.create(2.0);

    @Override
    protected Object cRun() {
        HttpServletRequest request = context.getRequest();
        if (rateLimiter.tryAcquire()){
            log.info("get Rate token success");
        }else{
            log.error(" rate limit :{}", request.getRequestURI());
             return fail(402,"error: rate limit");
        }
        return null;
    }

    @Override
    public int filterOrder() {
        //第二道过滤器
        return 2;
    }
}
