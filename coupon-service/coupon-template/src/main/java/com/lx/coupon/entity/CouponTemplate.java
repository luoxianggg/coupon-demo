package com.lx.coupon.entity;

/*
* 优惠券魔板实体类定义
* */

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lx.coupon.constant.CouponCategory;
import com.lx.coupon.constant.DistributeTarget;
import com.lx.coupon.constant.ProductLine;
import com.lx.coupon.converter.CouponCategoryConverter;
import com.lx.coupon.converter.DistributeTargetConverter;
import com.lx.coupon.converter.ProductLineConverter;
import com.lx.coupon.converter.RuleConverter;
import com.lx.coupon.serialization.CouponTemplateSerialize;
import com.lx.coupon.vo.TemplateRule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "coupon_template")
@JsonSerialize(using = CouponTemplateSerialize.class)
public class CouponTemplate  implements Serializable{
    @Column(name = "id" ,nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "available" ,nullable = false)
    private Boolean available;

    @Column(name = "expired",nullable = false)
    private Boolean expired;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "logo",nullable = false)
    private String logo;
    @Column(name = "intro",nullable = false)
    private String desc;
    @Column(name = "category",nullable = false)
    @Convert(converter = CouponCategoryConverter.class)
    private CouponCategory couponCategory;
    @Column(name = "product_line",nullable = false)
    @Convert(converter = ProductLineConverter.class)
    private ProductLine productLine;
    @Column(name = "coupon_count",nullable = false)
    private Integer count;
    @CreatedDate
    @Column(name = "create_time",nullable = false)
    private Date createDate;
    @Column(name = "user_id",nullable = false)
    private Long userId;
    @Column(name = "template_key",nullable = false)
    private String key;
    @Column(name = "target",nullable = false)
    @Convert(converter = DistributeTargetConverter.class)
    private DistributeTarget target;
    @Column(name = "rule",nullable = false)
    @Convert(converter = RuleConverter.class)
    private TemplateRule rule;

    public CouponTemplate(String name,String logo,String desc,String category,
                          Integer productLine,Integer count,Long userId,Integer target,TemplateRule rule){
        this.available = false;
        this.expired = false;
        this.name = name;
        this.logo = logo;
        this.desc = desc;
        this.couponCategory = couponCategory.of(category);
        this.productLine = ProductLine.of(productLine);
        this.count = count;
        this.userId = userId;
        //优惠券模板唯一编码= 产品线类型 + 日期+ id
        this.key = productLine.toString() + category + new SimpleDateFormat("yyyymmdd").format(new Date());

        this.target = DistributeTarget.of(target) ;
        this.rule = rule;
    }
}
