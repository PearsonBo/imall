package com.bo.imall.config;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.bo.imall.filter.AjaxUserFilter;
import com.bo.imall.filter.MyPermissionsAuthorizationFilter;
import com.bo.imall.model.CommonPermission;
import com.bo.imall.model.PermissionInfo;
import com.bo.imall.model.SpecificPermission;
import com.bo.imall.relam.LdapAndDbCredentialMatcher;
import com.bo.imall.relam.UserLdapAndDbRealm;
import lombok.Data;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@Configuration
@ConfigurationProperties("permission")
@Data
public class ShiroConfiguration {

    private PermissionInfo permissionInfo;

    @Bean(name = "ldapAndDbCredentialsMatcher")
    public HashedCredentialsMatcher getLdapAndDbCredentialsMatcher() {
        return new LdapAndDbCredentialMatcher();
    }

    @Bean(name = "userLdapAndDbRealm")
    public AuthorizingRealm getUserLdapAndDbRealm() {
        AuthorizingRealm realm = new UserLdapAndDbRealm();
        realm.setCredentialsMatcher(getLdapAndDbCredentialsMatcher());
        realm.setCachingEnabled(false);
        return realm;
    }

    @Bean(name = "sessionIdCookie")
    public SimpleCookie getSessionIdCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("sid");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(-1);
        return simpleCookie;
    }

    @Bean(name = "sessionIdGenerator")
    public JavaUuidSessionIdGenerator getSessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    @Bean(name = "sessionValidationScheduler")
    public QuartzSessionValidationScheduler getSessionValidationScheduler() {
        QuartzSessionValidationScheduler sessionValidationScheduler = new QuartzSessionValidationScheduler();
        sessionValidationScheduler.setSessionValidationInterval(1800000);
        sessionValidationScheduler.setSessionManager(getSessionManager());
        return sessionValidationScheduler;
    }

    @Bean(name = "sessionManager")
    public MySessionManager getSessionManager() {
        MySessionManager sessionManager = new MySessionManager();
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdCookieEnabled(true);
        sessionManager.setSessionIdCookie(getSimpleCookie());
        return sessionManager;
    }

    @Bean(name = "sessionIdCookie")
    public SimpleCookie getSimpleCookie() {
        SimpleCookie sessionIdCookie = new SimpleCookie("sid");
        sessionIdCookie.setMaxAge(-1);
        sessionIdCookie.setHttpOnly(true);
        return sessionIdCookie;
    }

    @Bean(name = "rememberMeCookie")
    public SimpleCookie getRememberMeCookie() {
        SimpleCookie rememberMeCookie = new SimpleCookie("rememberMe");
        rememberMeCookie.setHttpOnly(true);
        rememberMeCookie.setMaxAge(2592000);
        return rememberMeCookie;
    }

    @Bean(name = "rememberMeManager")
    public CookieRememberMeManager getRememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCipherKey(Base64.decode("GHxH6G3LFh8Zb3NwoRgfFA=="));
        rememberMeManager.setCookie(getRememberMeCookie());
        return rememberMeManager;
    }

    @Bean(name = "shiroCacheManager")
    public CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }


    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getUserLdapAndDbRealm());
        securityManager.setSessionManager(getSessionManager());
        securityManager.setRememberMeManager(getRememberMeManager());
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    @Bean(name = "methodInvokingFactoryBean")
    public MethodInvokingFactoryBean getMethodInvokingFactoryBean() {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvokingFactoryBean.setArguments(new Object[]{getSecurityManager()});
        return methodInvokingFactoryBean;
    }

    @Bean(name = "formAuthenticationFilter")
    public FormAuthenticationFilter getFormAuthenticationFilter() {
        FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
        formAuthenticationFilter.setUsernameParam("loginName");
        formAuthenticationFilter.setPasswordParam("password");
        formAuthenticationFilter.setRememberMeParam("rememberMe");
        formAuthenticationFilter.setFailureKeyAttribute("shiroLoginFailure");
        return formAuthenticationFilter;
    }

    @Bean
    public FilterRegistrationBean ajaxFormAuthenticationFilterRegistration(FormAuthenticationFilter formAuthenticationFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(formAuthenticationFilter);
        registration.setEnabled(false);
        return registration;
    }


    public LogoutFilter getLogoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/web/user/ajaxLogout");
        return logoutFilter;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(getSecurityManager());
        shiroFilter.setLoginUrl("/rest/admin/unAuth");
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("authc", getFormAuthenticationFilter());
        filters.put("user", getAjaxUserFilter());
        filters.put("logout", getLogoutFilter());
        filters.put("perms", getMyPermissionsAuthorizationFilter());
        shiroFilter.setFilters(filters);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/resources/**", "anon");
        filterChainDefinitionMap.put("/web/user/logout", "logout");
        filterChainDefinitionMap.put("/web/user/**", "anon");

        if (permissionInfo != null) {
            putCommonPermission(filterChainDefinitionMap);
            putSpecificPermission(filterChainDefinitionMap);
        }

        filterChainDefinitionMap.put("/security/**", "user");

        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilter;
    }

    /**
     * 填入特别指定的权限配置
     */
    private void putSpecificPermission(Map<String, String> filterChainDefinitionMap) {
        if (permissionInfo.getSpecificPermissionList() == null) {
            return;
        }
        for (SpecificPermission specificPermission : permissionInfo.getSpecificPermissionList()) {
            filterChainDefinitionMap.put(specificPermission.getUrl(), "user, perms[\"" + StringUtils.join(specificPermission.getPerms(), ",") + "\"]");
        }
    }

    /**
     * 填入通用的权限配置
     */
    private void putCommonPermission(Map<String, String> filterChainDefinitionMap) {
        if (permissionInfo.getCommonPermissionList() == null) {
            return;
        }
        for (CommonPermission commonPermission : permissionInfo.getCommonPermissionList()) {
            filterChainDefinitionMap.put(commonPermission.getPrefix() + "/create", "user, perms[\"" + commonPermission.getPrefix() + ":create\"]");
            filterChainDefinitionMap.put(commonPermission.getPrefix() + "/edit", "user, perms[\"" + commonPermission.getPrefix() + ":edit\"]");
            filterChainDefinitionMap.put(commonPermission.getPrefix() + "/find", "user, perms[\"" + commonPermission.getPrefix() + ":find\"]");
            filterChainDefinitionMap.put(commonPermission.getPrefix() + "/frozen", "user, perms[\"" + commonPermission.getPrefix() + ":frozen\"]");
            filterChainDefinitionMap.put(commonPermission.getPrefix() + "/active", "user, perms[\"" + commonPermission.getPrefix() + ":active\"]");
            filterChainDefinitionMap.put(commonPermission.getPrefix() + "/inactive", "user, perms[\"" + commonPermission.getPrefix() + ":inactive\"]");
        }
    }

    @Bean
    public AjaxUserFilter getAjaxUserFilter() {
        return new AjaxUserFilter();
    }

    @Bean
    public FilterRegistrationBean ajaxUserFilterRegistration(AjaxUserFilter ajaxUserFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(ajaxUserFilter);
        registration.setEnabled(false);
        return registration;
    }

    @Bean
    public MyPermissionsAuthorizationFilter getMyPermissionsAuthorizationFilter() {
        return new MyPermissionsAuthorizationFilter();
    }

    @Bean
    public FilterRegistrationBean myPermissionsAuthorizationFilterRegistration(MyPermissionsAuthorizationFilter myPermissionsAuthorizationFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(myPermissionsAuthorizationFilter);
        registration.setEnabled(false);
        return registration;
    }

}
