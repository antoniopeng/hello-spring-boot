package com.antoniopeng.hello.spring.boot.service;

import com.antoniopeng.hello.spring.boot.entity.Role;

import java.util.List;

public interface RoleService {

    /**
     * 根据用户 ID 查询用户关联的角色
     * @param userId 用户 ID
     * @return List
     */
    List<Role> selectByUserId(Integer userId);
}
