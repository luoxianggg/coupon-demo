package com.lx.coupon.filter;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

public abstract class AbstractPreFilter  extends  AbstractZuulFilter{

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }


}
