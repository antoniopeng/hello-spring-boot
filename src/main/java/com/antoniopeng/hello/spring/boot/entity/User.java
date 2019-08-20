package com.antoniopeng.hello.spring.boot.entity;

import javax.persistence.*;

public class User {
    /**
     * 用户主键
     */
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码（存储加密后的密码）
     */
    private String password;

    /**
     * 获取用户主键
     *
     * @return user_id - 用户主键
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户主键
     *
     * @param userId 用户主键
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码（存储加密后的密码）
     *
     * @return password - 密码（存储加密后的密码）
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码（存储加密后的密码）
     *
     * @param password 密码（存储加密后的密码）
     */
    public void setPassword(String password) {
        this.password = password;
    }
}