package com.lx.config.controller;

import com.lx.config.bean.RequestResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

public class UserController {
    private static final String Login_Status_Fail="1";

    private static final String Login_Status_Success="0";

    private static final String Login_Status_Expire="2";

    //1.loginfail登录失败
    @PostMapping("/loginfail")
    public RequestResult loginFail(){
        return new RequestResult(Login_Status_Fail,"用户名、密码错误",null);
    }

    //2.loginsuccess登录成功

    @PostMapping("/loginsucces/{token}")
    public RequestResult loginSuccess(@PathVariable String token){
        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("token",token);
        return new RequestResult(Login_Status_Success,"登录成功", paramMap);
    }

    //3.loginexpire
    @PostMapping("/loginexpire")
    public RequestResult loginExpire(){
        return new RequestResult(Login_Status_Expire,"缓存超时",null);
    }
}
