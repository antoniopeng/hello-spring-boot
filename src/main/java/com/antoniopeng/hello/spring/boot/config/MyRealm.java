package com.antoniopeng.hello.spring.boot.config;

import com.antoniopeng.hello.spring.boot.entity.Perm;
import com.antoniopeng.hello.spring.boot.entity.Role;
import com.antoniopeng.hello.spring.boot.entity.User;
import com.antoniopeng.hello.spring.boot.service.PermService;
import com.antoniopeng.hello.spring.boot.service.RoleService;
import com.antoniopeng.hello.spring.boot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * 自定义Realm，实现授权与认证
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermService permService;

    /**
     * 用户认证
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.selectByUsername(token.getUsername());
        if (user == null) {
            throw new UnknownAccountException();
        }
        // 生成盐值（用于加密密码和解密密码，这里以 “x867” 为密钥示范）
        ByteSource credentialsSalt = ByteSource.Util.bytes("x867");
        return new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt, getName());
    }

    /**
     * 用户授权
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            Collection<String> rolesCollection = new HashSet<>();
            Collection<String> permCollection = new HashSet<>();
            List<Role> roles = roleService.selectByUserId(user.getUserId());
            for (Role role : roles) {
                rolesCollection.add(role.getRoleName());
                for (Perm perm : permService.selectByRoleId(role.getRoleId())) {
                    permCollection.add(perm.getPermUrl());
                }
                info.addStringPermissions(permCollection);
            }
            info.addRoles(rolesCollection);
            return info;
        }
        return null;
    }
}