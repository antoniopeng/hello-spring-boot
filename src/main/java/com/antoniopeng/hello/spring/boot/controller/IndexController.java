package com.antoniopeng.hello.spring.boot.controller;

import com.antoniopeng.hello.spring.boot.common.AppReturn;
import com.antoniopeng.hello.spring.boot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login.html")
    @RequiresAuthentication
    @RequiresRoles(value = {""}, logical = Logical.OR)
    @RequiresPermissions(value = {""}, logical = Logical.OR)
    public String loginView() {

        // 判断当前用户是否通过认证
        if (SecurityUtils.getSubject().isAuthenticated()) {
            // 认证通过，重定向到首页
            return "redirect:index.html";
        } else {
            // 未认证或认证失败，转发到登录页
            return "login";
        }
    }

    @RequestMapping(value = "login.do")
    @ResponseBody
    public AppReturn loginDo(@RequestParam String username, @RequestParam String password) {
        return userService.loginDo(username, password);
    }

    @RequestMapping(value = "index.html")
    public String indexView() {
        return "index";
    }

    @RequestMapping(value = "logout.do")
    public String logoutDo() {

        if (SecurityUtils.getSubject().isAuthenticated()) {
            // 退出
            SecurityUtils.getSubject().logout();
        }
        return "redirect:login.html";
    }

    @RequestMapping(value = "unauthorized.html")
    public String unauthorizedView() {

        return "unauthorized";
    }
}
