package com.antoniopeng.hello.spring.boot.service.impl;

import com.antoniopeng.hello.spring.boot.entity.Role;
import com.antoniopeng.hello.spring.boot.mapper.RoleMapper;
import com.antoniopeng.hello.spring.boot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> selectByUserId(Integer userId) {

        return roleMapper.selectByUserId(userId);
    }
}
