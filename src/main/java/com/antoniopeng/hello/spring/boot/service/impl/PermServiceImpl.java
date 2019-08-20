package com.antoniopeng.hello.spring.boot.service.impl;

import com.antoniopeng.hello.spring.boot.entity.Perm;
import com.antoniopeng.hello.spring.boot.mapper.PermMapper;
import com.antoniopeng.hello.spring.boot.service.PermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermServiceImpl implements PermService {

    @Autowired
    private PermMapper permMapper;

    @Override
    public List<Perm> selectByRoleId(Integer roleId) {

        return permMapper.selectByRoleId(roleId);
    }
}
