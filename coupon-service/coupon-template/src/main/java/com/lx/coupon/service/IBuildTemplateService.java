package com.lx.coupon.service;

import com.lx.coupon.entity.CouponTemplate;
import com.lx.coupon.exception.CouponException;
import com.lx.coupon.vo.TemplateRequest;

/*
* 构建优惠券模板定义
* */
public interface IBuildTemplateService {
    /**
     * <h2>创建优惠券模板</h2>
     * @param request {@link com.lx.coupon.vo.TemplateRequest} 模板信息请求对象
     * @return {@link CouponTemplate} 优惠券模板实体
     * */
    CouponTemplate buildTemplate(TemplateRequest request)
            throws CouponException;
}
