package com.lx.coupon.constant;

/*
* 优惠券分类
* */

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum CouponCategory {

    MANJIAN("满减券","001"),
    ZHEKOU("折扣券","002"),
    LIJIAN("立减券","003");

    //分类描述
    private String description;
    //分类编码
    private  String code;

    public  static CouponCategory of(String code){
        Objects.requireNonNull(code);
        return Stream.of(values()).filter(bean -> bean.code.equals(code))
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException(code + "nor exists"));
    }
}
