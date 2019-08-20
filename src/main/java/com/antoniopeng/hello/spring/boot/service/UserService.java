package com.antoniopeng.hello.spring.boot.service;

import com.antoniopeng.hello.spring.boot.common.AppReturn;
import com.antoniopeng.hello.spring.boot.entity.User;

public interface UserService {

    /**
     * 根据用户名查询用户信息是否存在
     * @param username 用户名
     * @return User
     */
    User selectByUsername(String username);

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return AppReturn
     */
    AppReturn loginDo(String username, String password);
}
