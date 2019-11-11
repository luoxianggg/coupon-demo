package com.lx.coupon.advice;

import com.lx.coupon.annotation.IgnoreResponseAdvice;
import com.lx.coupon.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/*
* 统一相应处理
* */
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object>{

    /*
    * 判断是否需要对响应进行处理
    * */
    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
       //如果当前方法法所在的类标志了 @IgnoreResponseAdvice 注解，不需要处理
        if( methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return  false;
        }
        //如果当前方法标志了 @IgnoreResponseAdvice 注解，不需要处理
        if(methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)){
            return false;
        }
        return true;
    }
/*
* 相应返回之前的处理
* */
    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
       //定义最终的返回对象
        CommonResponse<Object> response = new CommonResponse<>(0,"");
        if(null == o){ //如过Controller返回的void 则不这是data
            return response;
        }else if(o instanceof CommonResponse){
            response = (CommonResponse<Object>) o;
        }else{//否则发相应对象最为commonresponse 的data部分
             response.setData(o);
        }
        return response;
    }
}
