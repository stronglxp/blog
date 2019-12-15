package com.codeliu.blog.config;

import com.codeliu.blog.realm.LoginRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Configuration of shiro
 */
@Configuration
public class ShiroConfig {

    /**
     * Credential matcher
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // MD5 encryption once
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;
    }

    /**
     * Custom realm
     * @return
     */
    @Bean
    public LoginRealm loginRealm() {

        LoginRealm loginRealm = new LoginRealm();
        loginRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return loginRealm;
    }

    /**
     * Security Manager
     * @return
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(loginRealm());
        return securityManager;
    }

    /**
     * life cycle
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * Enable annotations
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor as=new AuthorizationAttributeSourceAdvisor();
        as.setSecurityManager(securityManager());
        return as;
    }

    /**
     * Configure filtering rules
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        /**
         * Define Shiro filter chain map structure the path
         * represented by the first '/' of key (value value value in XML) in map
         * is relative to the value of HttpServletRequest.getcontextpath()
         *
         * anon:Its corresponding filter is empty and nothing is done. Here, do and * after.
         *      JSP indicate parameters, such as login.jsp?main
         * authc:The page under this filter can only be accessed after verification.
         *      It is a built-in interceptor of Shiro,
         *      org.apache.shiro.web.filter.authc.formauthenticationfilter
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // The back url should authc before access
        // The front url not
        filterChainDefinitionMap.put("/back/**", "authc,roles[admin]");
        filterChainDefinitionMap.put("/front/**", "anon");
        //filterChainDefinitionMap.put("/back/**", "anon");
        // Login without authentication
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/", "anon");
        // Static resources do not require authentication
        filterChainDefinitionMap.put("/static/**", "anon");
        /**
         * Except for the above amount /login,
         * which can be accessed anonymously, other paths need login access
         *
         * If you are not logged in, accessing other paths will jump to /login
         */
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
}
