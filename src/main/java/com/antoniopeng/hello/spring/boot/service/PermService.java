package com.antoniopeng.hello.spring.boot.service;

import com.antoniopeng.hello.spring.boot.entity.Perm;

import java.util.List;

public interface PermService {

    /**
     * 根据角色 ID 查询关联的权限信息
     * @param roleId 角色 ID
     * @return List
     */
    List<Perm> selectByRoleId(Integer roleId);
}
