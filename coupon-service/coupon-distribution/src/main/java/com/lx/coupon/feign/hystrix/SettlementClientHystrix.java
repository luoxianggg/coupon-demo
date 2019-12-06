package com.lx.coupon.feign.hystrix;

import com.lx.coupon.exception.CouponException;
import com.lx.coupon.feign.SettlementClient;
import com.lx.coupon.vo.CommonResponse;
import com.lx.coupon.vo.SettlementInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>结算微服务熔断策略实现</h1>
 */
@Slf4j
@Component
public class SettlementClientHystrix implements SettlementClient{
    @Override
    public CommonResponse<SettlementInfo> computeRule(SettlementInfo settlement) throws CouponException {
        log.error("[eureka-client-coupon-settlement] computeRule" +
                "request error");

        settlement.setEmploy(false);
        settlement.setCost(-1.0);

        return new CommonResponse<>(
                -1,
                "[eureka-client-coupon-settlement] request error",
                settlement
        );
    }
}
