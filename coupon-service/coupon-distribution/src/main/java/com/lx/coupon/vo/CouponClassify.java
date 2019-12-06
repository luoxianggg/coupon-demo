package com.lx.coupon.vo;

import com.lx.coupon.constant.CouponStatus;
import com.lx.coupon.constant.PeriodType;
import com.lx.coupon.entity.Coupon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.time.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CouponClassify {
    private List<Coupon> usable;
    private List<Coupon> used;
    private List<Coupon> expired;
    //对当前优惠券进行分类
    public  static CouponClassify couponClassify(List<Coupon> couponList){
        List<Coupon> usable = new ArrayList<>(couponList.size());
        List<Coupon> used = new ArrayList<>(couponList.size());
        List<Coupon> expired = new ArrayList<>(couponList.size());

        couponList.forEach(
                c -> {
                    boolean isTimeExpired;
                    long curTime = new Date().getTime();
                    if (c.getTemplateSDK().getRule().getExpiration().getPeriod().equals(
                            PeriodType.REGULAR.getCode()
                    )){
                        isTimeExpired = c.getTemplateSDK().getRule().getExpiration().getDeadLine() <= curTime;
                    }else{
                        isTimeExpired = DateUtils.addDays(c.getAssignTime(),c.getTemplateSDK().getRule().getExpiration().getGap()).getTime() <= curTime;
                    }
                    if (c.getStatus() == CouponStatus.USED
                            ){
                        used.add(c);
                    }else if(c.getStatus() == CouponStatus.EXPIRED || isTimeExpired){
                        expired.add(c);

                    }else{
                        usable.add(c);
                    }
                }
        );
        return new CouponClassify(usable,used,expired);
    }
}
