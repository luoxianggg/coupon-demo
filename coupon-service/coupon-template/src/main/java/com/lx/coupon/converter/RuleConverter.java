package com.lx.coupon.converter;

import com.alibaba.fastjson.JSON;
import com.lx.coupon.vo.TemplateRule;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

/*
* 优惠券规则转换器
* */
@Convert
public class RuleConverter implements AttributeConverter<TemplateRule,String>{
    @Override
    public String convertToDatabaseColumn(TemplateRule rule) {
        return JSON.toJSONString(rule);
    }

    @Override
    public TemplateRule convertToEntityAttribute(String rule) {
        return JSON.parseObject(rule, TemplateRule.class);
    }
}
