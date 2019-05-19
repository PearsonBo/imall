package com.bo.imall.dao.impl;

import com.bo.imall.dao.AdminUserDao;
import com.bo.imall.dao.BaseNoHistoryDaoImpl;
import com.bo.imall.dao.Mapper;
import com.bo.imall.mapper.admin.AdminUserMapper;
import com.bo.imall.model.admin.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 系统生成Dao实现类
 * @author admin
 * @date 2017/11/17
 */
@Repository
public class AdminUserDaoImpl extends BaseNoHistoryDaoImpl<AdminUser> implements AdminUserDao {

    @Autowired
    private AdminUserMapper mapper;

    @Override
    protected Mapper<AdminUser> getMapper() {
        return mapper;
    }

}
