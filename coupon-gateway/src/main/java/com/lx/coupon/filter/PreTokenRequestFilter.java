package com.lx.coupon.filter;

import com.lx.coupon.bean.SysUser;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/*
* 在过滤器中存储发起请求的开始时间戳
* */
public class PreTokenRequestFilter extends  AbstractPreFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(PreTokenRequestFilter.class);

    private static final int FILTER_ORDER =  1;
    private static final boolean  SHOULD_FILTER=false;
    public static final String AUTH_TOKEN     = "Authorization";
    private static final Logger logger = LoggerFactory.getLogger(PreTokenRequestFilter.class);



    @Autowired
    RestTemplate restTemplate;


    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    private boolean isAuthTokenPresent() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String token =  ctx.getRequest().getHeader(AUTH_TOKEN);
        if (token !=null){
            return true;
        }

        return false;
    }

    private SysUser isAuthTokenValid(){
        ResponseEntity<SysUser> restExchange = null;
        RequestContext ctx = RequestContext.getCurrentContext();
        String token =  ctx.getRequest().getHeader(AUTH_TOKEN);
        Map<String, String> urlParams = new HashMap<>(1);
        urlParams.put("token", String.valueOf(token));
        String uri = "http://127.0.0.1:8004/validate/{token}";
        SysUser sysUser = new SysUser();
        try {
            sysUser = restTemplate.getForObject(uri, SysUser.class,urlParams);;
        }
        catch(HttpClientErrorException ex){
            if (ex.getStatusCode()== HttpStatus.UNAUTHORIZED) {
                return null;
            }

            throw ex;
        }


        return sysUser;
    }
    @Override
    protected Object cRun() {
        context.set("startTime",System.currentTimeMillis());
        RequestContext ctx = RequestContext.getCurrentContext();

        //If we are dealing with a call to the authentication service, let the call go through without authenticating
        if ( ctx.getRequest().getRequestURI().equals("/authenticate")){
            return null;
        }

        if (isAuthTokenPresent()){
            logger.debug("Authentication token is present.");
        }else{
            logger.debug("Authentication token is not present.");

            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            ctx.setSendZuulResponse(false);
        }

        SysUser userInfo = isAuthTokenValid();
        if (userInfo!=null){
            //filterUtils.setUserId(userInfo.getUserId());
            //filterUtils.setOrgId(userInfo.getOrganizationId());

            logger.debug("Authentication token is valid.");
            return null;
        }

        logger.debug("Authentication token is not valid.");
        ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        ctx.setSendZuulResponse(false);

        return null;
    }

}
