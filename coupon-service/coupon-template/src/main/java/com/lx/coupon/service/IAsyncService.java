package com.lx.coupon.service;

import com.lx.coupon.entity.CouponTemplate;

/*
* 异步服务接口定义
* */
public interface IAsyncService {

    /**
     * <h2>根据模板异步的创建优惠券码</h2>
     * @param template {@link com.lx.coupon.entity.CouponTemplate} 优惠券模板实体
     * */
    void asyncConstructCouponByTemplate(CouponTemplate template);
}
