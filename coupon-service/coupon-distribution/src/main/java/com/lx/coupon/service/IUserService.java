package com.lx.coupon.service;

import com.lx.coupon.entity.Coupon;
import com.lx.coupon.exception.CouponException;
import com.lx.coupon.vo.AcquireTemplateRequest;
import com.lx.coupon.vo.CouponTemplateSDK;
import com.lx.coupon.vo.SettlementInfo;

import java.util.List;

/*
* 用户相关的借口定义
* 用户三类优惠券信息的展示
* 查看用户当前可以领取的优惠券模板 - coupon-template 微服务配合实现
* 用户领取优惠券服务
* 用户消费优惠券服务 coupon-settlement 服务
* */
public interface IUserService {
    /**
     * <h2>根据用户 id 和状态查询优惠券记录</h2>
     * @param userId 用户 id
     * @param status 优惠券状态
     * @return {@link Coupon}s
     * */
    List<Coupon> findCouponsByStatus(Long userId, Integer status)
            throws CouponException;

    /**
     * <h2>根据用户 id 查找当前可以领取的优惠券模板</h2>
     * @param userId 用户 id
     * @return {@link com.lx.coupon.vo.CouponTemplateSDK}s
     * */
    List<CouponTemplateSDK> findAvailableTemplate(Long userId)
            throws CouponException;

    /**
     * <h2>用户领取优惠券</h2>
     * @param request {@link com.lx.coupon.vo.AcquireTemplateRequest}
     * @return {@link Coupon}
     * */
    Coupon acquireTemplate(AcquireTemplateRequest request)
            throws CouponException;

    /**
     * <h2>结算(核销)优惠券</h2>
     * @param info {@link SettlementInfo}
     * @return {@link SettlementInfo}
     * */
    SettlementInfo settlement(SettlementInfo info) throws CouponException;
}
