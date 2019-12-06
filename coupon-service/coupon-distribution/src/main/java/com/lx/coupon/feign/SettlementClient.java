package com.lx.coupon.feign;

import com.lx.coupon.exception.CouponException;
import com.lx.coupon.vo.CommonResponse;
import com.lx.coupon.vo.SettlementInfo;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "eureke-client-coupon-settlement")
public interface SettlementClient {
    /**
     * <h2>优惠券规则计算</h2>
     * */
    @RequestMapping(value = "/coupon-settlement/settlement/compute",
            method = RequestMethod.POST)
    CommonResponse<SettlementInfo> computeRule(
            @RequestBody SettlementInfo settlement) throws CouponException;
}
