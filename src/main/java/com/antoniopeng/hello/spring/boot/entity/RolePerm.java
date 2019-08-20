package com.antoniopeng.hello.spring.boot.entity;

import javax.persistence.*;

@Table(name = "role_perm")
public class RolePerm {
    /**
     * 角色主键
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 权限主键
     */
    @Column(name = "perm_id")
    private Integer permId;

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

    /**
     * 获取权限主键
     *
     * @return perm_id - 权限主键
     */
    public Integer getPermId() {
        return permId;
    }

    /**
     * 设置权限主键
     *
     * @param permId 权限主键
     */
    public void setPermId(Integer permId) {
        this.permId = permId;
    }
}