package com.lx.coupon.vo;

/*
* 商品信息
* */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsInfo {
    //商品类型
    private Integer type;
    //价格
    private Double price;
    //数量
    private Integer count;
}
