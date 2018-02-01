package com.kinoymir.chat.config.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(chatRealm());
        return securityManager;
    }

    @Bean
    public ChatRelam chatRealm() {
        return new ChatRelam();
    }

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/user/notLogin");
        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/user/403");

        // 拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/register", "anon");

        /**
         * 后台登录页
         */
        filterChainDefinitionMap.put("/back/login", "anon");
        //不需要验证身份
        //filterChainDefinitionMap.put("/user", "anon");


        // 需要验证身份
        filterChainDefinitionMap.put("/user/**", "authc");

        // 需要验证身份
        filterChainDefinitionMap.put("/back/**", "roles[admin]");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

}
