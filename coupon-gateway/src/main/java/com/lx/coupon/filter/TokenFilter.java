package com.lx.coupon.filter;

import javax.servlet.http.HttpServletRequest;

/*
*
* 校验请求中传递的token
* */
public class TokenFilter extends AbstractPreFilter {
    @Override
    protected Object cRun() {
        HttpServletRequest request = context.getRequest();
        log.info(String.format("%s request to %s",request.getMethod(),request.getRequestURI().toString()));
        Object token = request.getParameter("token");
        if ( null == token){
            log.error("error: token is empty");
            return  fail(401,"error is empty");
        }
        return success();
    }

    @Override
    public int filterOrder() {
        return 1;
    }
}
