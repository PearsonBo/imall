package com.bo.imall.helper;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bo.imall.config.SystemConfig;
import com.bo.imall.dao.AdminUserDao;
import com.bo.imall.model.admin.AdminUser;
import com.bo.imall.model.admin.AdminUserSo;
import com.bo.imall.model.admin.AdminUserVo;
import com.bo.imall.service.RedisService;
import com.bo.imall.util.JsonMapper;
import com.bo.imall.util.JsonMapperFactory;
import com.bo.imall.util.SecurityKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: PearsonBo
 * @Date: 2019-05-24 15:37
 */
@Service
public class ShiroRedisHelper {

    protected final JsonMapper jsonMapper = JsonMapperFactory.getJsonMapper();

    @Reference(version = SystemConfig.SPI_VERSION)
    private RedisService redisService;

    @Autowired
    private AdminUserDao adminUserDao;

    /**
     * 缓存中获取登录用户
     *
     * @param sessionId
     * @return
     */
    public AdminUserVo getOperator(String sessionId) {
        if (sessionId == null) {
            return null;
        }
        String key = SecurityKeyUtils.geneSessionOperatorKey(sessionId);
        String adminUserJson = (String) redisService.get(key);
        return jsonMapper.fromJson(adminUserJson, AdminUserVo.class);
    }

    /**
     * 获取用户的角色集合
     *
     * @param sessionId
     * @param adminUserId
     * @return
     */
    public Set<String> getRoleList(String sessionId, Long adminUserId) {
        return getByKey(sessionId, adminUserId, SecurityKeyUtils.geneSessionOperatorRolesKey(sessionId, adminUserId));
    }

    /**
     * 获取用户的权限集合
     *
     * @param sessionId
     * @param adminUserId
     * @return
     */
    public Set<String> getPermList(String sessionId, Long adminUserId) {
        return getByKey(sessionId, adminUserId, SecurityKeyUtils.geneSessionOperatorPermsKey(sessionId, adminUserId));
    }

    @SuppressWarnings("unchecked")
    private Set<String> getByKey(String sessionId, Long adminUserId, String key) {
        if (sessionId == null || adminUserId == null) {
            return null;
        }
        String rolesKey = key;
        String rolesJson = (String) redisService.get(rolesKey);
        return (Set<String>) jsonMapper.fromJson(rolesJson, Set.class);
    }

    /**
     * 保存用户到缓存中
     * // TODO: 2019-05-24 哪一步保存
     *
     * @param sessionId
     * @param loginName
     */
    public void saveOperator(String sessionId, String loginName) {
        if (sessionId == null || loginName == null) {
            return;
        }

        AdminUserSo adminUserSo = new AdminUserSo();
        adminUserSo.setUsername(loginName);
        AdminUser adminUser = adminUserDao.findBoBySo(adminUserSo);


        saveOperatorJson(sessionId, adminUser);

        saveRolesJson(sessionId, adminUser);

        savePermsJson(sessionId, adminUser);

    }

    private void savePermsJson(String sessionId, AdminUser adminUser) {
        String permsKey = SecurityKeyUtils.geneSessionOperatorPermsKey(sessionId, adminUser.getId());
        // select from db
        Set<String> perms = new HashSet<>(Arrays.asList("/no:perm", "/some:perms"));
        String permsJson = jsonMapper.toJson(perms);
        redisService.set(permsKey, permsJson);
    }

    private void saveRolesJson(String sessionId, AdminUser adminUser) {
        String rolesKey = SecurityKeyUtils.geneSessionOperatorRolesKey(sessionId, adminUser.getId());
        // select from db
        Set<String> roles = new HashSet<>(Arrays.asList("/no:role", "/some:roles"));
        String rolesJson = jsonMapper.toJson(roles);
        redisService.set(rolesKey, rolesJson);
    }

    private void saveOperatorJson(String sessionId, AdminUser adminUser) {
        String operatorKey = SecurityKeyUtils.geneSessionOperatorKey(sessionId);
        String operatorJson = jsonMapper.toJson(adminUser);
        redisService.set(operatorKey, operatorJson);
    }
}
