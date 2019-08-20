package com.antoniopeng.hello.spring.boot.entity;

import javax.persistence.*;

public class Perm {
    /**
     * 权限主键
     */
    @Id
    @Column(name = "perm_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer permId;

    /**
     * 权限url
     */
    @Column(name = "perm_url")
    private String permUrl;

    /**
     * 权限描述
     */
    @Column(name = "perm_description")
    private String permDescription;

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

    /**
     * 获取权限url
     *
     * @return perm_url - 权限url
     */
    public String getPermUrl() {
        return permUrl;
    }

    /**
     * 设置权限url
     *
     * @param permUrl 权限url
     */
    public void setPermUrl(String permUrl) {
        this.permUrl = permUrl;
    }

    /**
     * 获取权限描述
     *
     * @return perm_description - 权限描述
     */
    public String getPermDescription() {
        return permDescription;
    }

    /**
     * 设置权限描述
     *
     * @param permDescription 权限描述
     */
    public void setPermDescription(String permDescription) {
        this.permDescription = permDescription;
    }
}