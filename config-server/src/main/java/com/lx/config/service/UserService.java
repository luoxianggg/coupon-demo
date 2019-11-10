package com.lx.config.service;

import com.lx.config.bean.SCCUser;

public interface UserService {
    SCCUser login(String username, String password);

    boolean userExistInCache(String token);
}
