package com.antoniopeng.hello.spring.boot.entity;

import javax.persistence.*;

@Table(name = "user_role")
public class UserRole {
    /**
     * 用户主键
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 角色主键
     */
    @Column(name = "role_id")
    private Integer roleId;

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
     * 获取角色主键
     *
     * @return role_id - 角色主键
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色主键
     *
     * @param roleId 角色主键
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}