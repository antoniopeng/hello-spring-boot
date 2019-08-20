package com.antoniopeng.hello.spring.boot.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShiroConfig {

    /**
     * 配置密码加密
     */
    @Bean("hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {

        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法（加密）
        credentialsMatcher.setHashAlgorithmName("MD5");
        // 散列次数（加密次数）
        credentialsMatcher.setHashIterations(1);
        // storedCredentialsHexEncoded 默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    /**
     * 注入自定义的 Realm
     */
    @Bean("MyRealm")
    public MyRealm MyRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher matcher) {

        MyRealm MyRealm = new MyRealm();
        MyRealm.setCredentialsMatcher(matcher);
        return MyRealm;
    }

    /**
     * 配置自定义权限过滤规则
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        bean.setSuccessUrl("/index.html");
        bean.setLoginUrl("/login.html");
        bean.setUnauthorizedUrl("/unauthorized.html");

        /**
         * anon：匿名用户可访问
         * authc：认证用户可访问
         * user：使用rememberMe可访问
         * perms：对应权限可访问
         * role：对应角色权限可访问
         **/
        Map<String, String> filterMap = new LinkedHashMap<>();
        /**
         * 允许匿名访问静态资源
         */
        filterMap.put("/image/**", "anon");
        filterMap.put("/css/**", "anon");
        filterMap.put("/js/**", "anon");
        filterMap.put("/plugin/**", "anon");
        /**
         * 允许匿名访问登录页面和登录操作
         */
        filterMap.put("/login.html", "anon");
        filterMap.put("/login.do", "anon");
        /**
         * 其它请求需要登录认证后才能访问
         */
        filterMap.put("/**", "authc");
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }

    /**
     * 注入 securityManager
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(HashedCredentialsMatcher hashedCredentialsMatcher, @Qualifier("sessionManager") DefaultWebSessionManager defaultWebSessionManager) {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(MyRealm(hashedCredentialsMatcher));
        securityManager.setSessionManager(defaultWebSessionManager);
        return securityManager;
    }

    /**
     * 开启注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {

        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {

        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * 配置异常页面
     */
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {

        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        // 未认证跳转页面
        properties.setProperty("org.apache.shiro.authz.UnauthenticatedException", "login");
        // 权限不足跳转页面
        properties.setProperty("org.apache.shiro.authz.UnauthorizedException", "unauthorized");
        resolver.setExceptionMappings(properties);
        return resolver;
    }

    /**
     * 会话管理器
     */
    @Bean("sessionManager")
    public DefaultWebSessionManager defaultWebSessionManager() {

        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        // 设置用户 session 信息失效时间（单位：ms）
        defaultWebSessionManager.setGlobalSessionTimeout(300000000);
        return defaultWebSessionManager;
    }

    /**
     * 重置 ShiroDialect，省略此步将不能在 Thymeleaf 页面使用 Shiro 标签
     */
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
