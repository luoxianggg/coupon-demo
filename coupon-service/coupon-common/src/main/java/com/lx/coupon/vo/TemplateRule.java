package com.lx.coupon.vo;

import com.lx.coupon.constant.PeriodType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/*
* 优惠券规则定义
* */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class TemplateRule {
    //优惠期过期规则
    private  Expiration expiration;
    //优惠券则扣规则
    private  Discount discount;
    //优惠券最多能拿几张
    private  Integer limitation;
    //使用范围
   private  Usage usage;
   //权重（可以和哪些优惠券叠加使用，同一类优惠券不能叠加）
    private  String weight;

    //有效期规则
    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    public static class Expiration{
        //有效期规则，对应Periodtype de code 字段
        private  Integer period;
        //有效间隔，只对变动性有效期有效
        private Integer gap;
        //优惠券失效日期
        private Long deadLine;
        boolean validate(){
            return null != PeriodType.of(period) && gap > 0 && deadLine > 0;
        }
    }
    /*
    * 折扣 ，要与类型配合决定
    * */
    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    public static  class Discount{

        //额度：满减20，折扣85，立减10
        private  Integer quata;
        //满多少才能用
        private Integer base;

        boolean validate(){
            return quata > 0 && base > 0;
        }
    }
    /*
    * 使用范围
    * */
    @AllArgsConstructor
    @Data
    @NoArgsConstructor
    public static  class Usage{
        private  String province;
        private String city;
        //商品类型
        private  String goodsTppe;
        boolean validate(){
            return StringUtils.isNotEmpty(province) &&
                    StringUtils.isNotEmpty(city) &&
                    StringUtils.isNotEmpty(goodsTppe);
        }
    }
}
