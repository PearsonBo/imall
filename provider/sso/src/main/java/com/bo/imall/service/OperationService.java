package com.bo.imall.service;

import com.bo.imall.model.OperatorVo;

import java.util.Set;

/**
 * @author Hu Jianbo
 * @date: 2018/7/24
 */
public interface OperationService {

    public OperatorVo findVoByLoginName(String operatorName);

    Set<String> getRoles(Long id);

    Set<String> getPerms(Long id);
}
