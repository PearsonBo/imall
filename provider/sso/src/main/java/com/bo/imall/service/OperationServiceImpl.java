package com.bo.imall.service;

import com.bo.imall.model.OperatorVo;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class OperationServiceImpl implements OperationService {

    @Override
    public OperatorVo findVoByLoginName(String operatorName) {
        OperatorVo operatorVo = new OperatorVo();
        operatorVo.setLoginName("bo");
        operatorVo.setPassword("111111");
        operatorVo.setId(2012216434L);
        return operatorVo;
    }

    @Override
    public Set<String> getRoles(Long id) {
        return new HashSet<String>(Arrays.asList("role1", "role2", "role3"));
    }

    @Override
    public Set<String> getPerms(Long id) {
        return new HashSet<String>(Arrays.asList("perm1", "perm2", "perm3"));
    }
}
