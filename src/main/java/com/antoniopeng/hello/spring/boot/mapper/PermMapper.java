package com.antoniopeng.hello.spring.boot.mapper;

import com.antoniopeng.hello.spring.boot.common.MyMapper;
import com.antoniopeng.hello.spring.boot.entity.Perm;

import java.util.List;

public interface PermMapper extends MyMapper<Perm> {

    List<Perm> selectByRoleId(Integer roleId);
}