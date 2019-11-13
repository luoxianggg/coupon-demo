package com.lx.coupon.exception;

/*
* 全局异常处理
* */

import com.lx.coupon.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

//对所有Rest请求拦截
@RestControllerAdvice
public class GlobalExceptionAvdice {
    /*
    * 对指定异常进行拦截，统一处理
    * */
    @ExceptionHandler(value = CouponException.class)
    public CommonResponse<String> handlerCouponException(HttpServletRequest request,CouponException ex){
        CommonResponse<String> response = new CommonResponse<>(-1,"business error");
        return response;
    }
}
