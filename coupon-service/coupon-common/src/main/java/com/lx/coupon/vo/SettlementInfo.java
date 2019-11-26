package com.lx.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
*结算信息对象信息定义
* 包含：
* 1、user_id
* 2、 商品列表
* 3、 优惠券列表
* 4、 结算结果金额
* */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettlementInfo {
    //用户ID
    private  Long userId;
    //商品信息列表
    private List<GoodsInfo> goodsInfos;
    //优惠券列表
    private  List<CouponAndTemplateInfo>couponAndTemplateInfos;
    //是否结算生效 即核销
    private Boolean employ;
    //优惠券结算金额
    private Double cost;
    //优惠券模板信息
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public  static  class  CouponAndTemplateInfo{
        private  Integer id;
        private CouponTemplateSDK couponTemplateSDK;
    }
}
