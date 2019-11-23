package com.lx.coupon.converter;

import com.lx.coupon.constant.DistributeTarget;

import javax.persistence.AttributeConverter;

public class DistributeTargetConverter implements AttributeConverter<DistributeTarget,Integer>{
    @Override
    public Integer convertToDatabaseColumn(DistributeTarget distributeTarget) {
        return distributeTarget.getCode();
    }

    @Override
    public DistributeTarget convertToEntityAttribute(Integer code) {
        return DistributeTarget.of(code);
    }
}
