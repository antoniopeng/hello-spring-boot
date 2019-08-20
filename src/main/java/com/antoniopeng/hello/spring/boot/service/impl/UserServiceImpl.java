package com.antoniopeng.hello.spring.boot.service.impl;

import com.antoniopeng.hello.spring.boot.common.AppReturn;
import com.antoniopeng.hello.spring.boot.entity.User;
import com.antoniopeng.hello.spring.boot.mapper.UserMapper;
import com.antoniopeng.hello.spring.boot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByUsername(String username) {

        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username);
        return userMapper.selectOneByExample(example);
    }

    @Override
    public AppReturn loginDo(String username, String password) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            return AppReturn.defeated("账号不存在");
        } catch (IncorrectCredentialsException e) {
            return AppReturn.defeated("密码错误");
        }
        return AppReturn.succeed("登录成功");
    }
}
