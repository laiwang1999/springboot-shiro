package com.example.springbootshiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(
            @Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        //添加shiro过滤器
        /*
        anon:无需认证就可访问
        authc:必须认证了才能访问
        user:必须拥有 记住我功能才能用
        perms:拥有对某个资源的权限
        role:拥有某个角色权限才能访问
         */
        //        filterMap.put("/user/add", "authc");
//        filterMap.put("/user/update", "authc");
        //登录拦截
        Map<String, String> filterMap = new LinkedHashMap<>();
        //授权,正常情况下，没有授权会跳转到未授权界面
        filterMap.put("/user/add", "perms[user:add]");
        filterMap.put("/user/update", "perms[user:update]");
        filterMap.put("/user/*", "authc");
        bean.setFilterChainDefinitionMap(filterMap);
        bean.setSecurityManager(defaultWebSecurityManager);
        //设置登录的请求
        bean.setLoginUrl("/toLogin");
//        未授权界面
        bean.setUnauthorizedUrl("/noauth");
        return bean;
    }

    //defaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(
            @Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        webSecurityManager.setRealm(userRealm);
        return webSecurityManager;
    }

    //创建realm对象,自定义类
    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }

    //整合ShiroDialect，用来整合shiro-thymeleaf
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }
}
