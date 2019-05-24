package com.bo.imall.relam;

import com.bo.imall.dao.AdminUserDao;
import com.bo.imall.helper.ShiroRedisHelper;
import com.bo.imall.model.admin.AdminUser;
import com.bo.imall.model.admin.AdminUserSo;
import com.bo.imall.model.admin.AdminUserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Set;

@Slf4j
public class UserLdapAndDbRealm extends AuthorizingRealm {

    private static final String REALM_NAME = "userLdapAndDbRealm";

    @Autowired
    private ShiroRedisHelper shiroRedisHelper;

    @Autowired
    private AdminUserDao adminUserDao;

    @Override
    public String getName() {
        return REALM_NAME;
    }

    /**
     * 获取用户信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("开始调用UserLdapAndDbRealm");
        return getAccountAuthenticationInfo(token);
    }

    /**
     * 获取用户权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null || subject.getPrincipal() == null) {
            throw new AuthorizationException("exc_user_user_not_existed");
        }

        String sessionId = subject.getSession().getId().toString();

        AdminUserVo adminUserVo = shiroRedisHelper.getOperator(sessionId);
        if (adminUserVo == null) {
            throw new IncorrectCredentialsException("系统中不存在该用户，请联系管理员维护");
        }

        Set<String> roles = shiroRedisHelper.getRoleList(sessionId, adminUserVo.getId());
        Set<String> perms = shiroRedisHelper.getPermList(sessionId, adminUserVo.getId());

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(perms);
        return authorizationInfo;

    }

    private AuthenticationInfo getAccountAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("开始用户验证");
        String operatorName = (String) token.getPrincipal();

        if (StringUtils.isEmpty(operatorName)) {
            throw new IncorrectCredentialsException();
        }

        AdminUserSo adminUserSo = new AdminUserSo();
        adminUserSo.setUsername(operatorName);
        AdminUser adminUser = adminUserDao.findBoBySo(adminUserSo);
        if (adminUser == null) {
            throw new IncorrectCredentialsException("系统中不存在该用户，请联系管理员维护");
        }

        // 交给 CredentialMatcher 进行密码匹配
        return new SimpleAuthenticationInfo(adminUser.getUsername(), adminUser, REALM_NAME);
    }

}
