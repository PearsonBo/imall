package com.bo.imall.helper;

import com.bo.imall.model.OperatorVo;
import com.bo.imall.service.OperationService;
import com.bo.imall.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author
 * @date 2018/5/4
 */
@Service
public class SecurityRedisHelper {

    @Autowired
    private OperationService operationService;

    @Autowired
    private RedisReadHelper<OperatorVo> redisReadHelper;

    protected final JsonMapper jsonMapper = JsonMapperFactory.getJsonMapper();

    public void deleteOperator(String sessionId) {
        String key = SecurityKeyUtils.geneSessionOperatorKey(sessionId);
        redisReadHelper.deleteObject(key);
    }

    public OperatorVo getOperator(ServletRequest request) {
        if (request instanceof HttpServletRequest) {
            return getOperator((HttpServletRequest) request);
        }
        return null;
    }

    public OperatorVo getOperator(HttpServletRequest request) {
        String sessionId = AuthorizationUtil.getSessionId(request);
        if (sessionId == null) {
            return null;
        }
        return getOperator(sessionId);
    }

    @SuppressWarnings("unchecked")
    public Set<String> getRoles(String sessionId, Long operatorId) {
        if (sessionId == null || operatorId == null) {
            return null;
        }
        String rolesKey = SecurityKeyUtils.geneSessionOperatorRolesKey(sessionId, operatorId);
        String rolesJson = redisReadHelper.getStringInRedis(rolesKey);
        return (Set<String>) jsonMapper.fromJson(rolesJson, Set.class);

    }

    @SuppressWarnings("unchecked")
    public Set<String> getPerms(String sessionId, Long operatorId) {
        if (sessionId == null || operatorId == null) {
            return null;
        }
        String permsKey = SecurityKeyUtils.geneSessionOperatorPermsKey(sessionId, operatorId);
        String permsJson = redisReadHelper.getStringInRedis(permsKey);
        return (Set<String>) jsonMapper.fromJson(permsJson, Set.class);
    }

    public OperatorVo getOperator(String sessionId) {
        if (sessionId == null) {
            return null;
        }
        String key = SecurityKeyUtils.geneSessionOperatorKey(sessionId);
        String operatorJson = redisReadHelper.getStringInRedis(key);
        return jsonMapper.fromJson(operatorJson, OperatorVo.class);
    }

    public void saveOperator(String sessionId, String loginName) {
        if (sessionId == null || loginName == null) {
            return;
        }

        OperatorVo operator = operationService.findVoByLoginName(loginName);

        saveOperatorJson(sessionId, operator);

        saveRolesJson(sessionId, operator);

        savePermsJson(sessionId, operator);

    }

    private void savePermsJson(String sessionId, OperatorVo operator) {
        String permsKey = SecurityKeyUtils.geneSessionOperatorPermsKey(sessionId, operator.getId());
        // select from db
        Set<String> perms = new HashSet<>(Arrays.asList("/no:perm", "/some:perms"));
        String permsJson = jsonMapper.toJson(perms);
        redisReadHelper.saveStringInRedis(permsKey, permsJson);
    }

    private void saveRolesJson(String sessionId, OperatorVo operator) {
        String rolesKey = SecurityKeyUtils.geneSessionOperatorRolesKey(sessionId, operator.getId());
        // select from db
        Set<String> roles = new HashSet<>(Arrays.asList("/no:role", "/some:roles"));
        String rolesJson = jsonMapper.toJson(roles);
        redisReadHelper.saveStringInRedis(rolesKey, rolesJson);
    }

    private void saveOperatorJson(String sessionId, OperatorVo operator) {
        String operatorKey = SecurityKeyUtils.geneSessionOperatorKey(sessionId);
        String operatorJson = jsonMapper.toJson(operator);
        redisReadHelper.saveStringInRedis(operatorKey, operatorJson);
    }

}
