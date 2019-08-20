package com.antoniopeng.hello.spring.boot.mapper;

import com.antoniopeng.hello.spring.boot.common.MyMapper;
import com.antoniopeng.hello.spring.boot.entity.Role;

import java.util.List;

public interface RoleMapper extends MyMapper<Role> {

    List<Role> selectByUserId(Integer userId);
}