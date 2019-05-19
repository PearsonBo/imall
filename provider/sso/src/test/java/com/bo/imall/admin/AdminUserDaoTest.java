package com.bo.imall.admin;

import com.bo.imall.SsoApplication;
import com.bo.imall.dao.AdminUserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SsoApplication.class)
public class AdminUserDaoTest {

    @Autowired
    private AdminUserDao adminUserDao;

    @Test
    public void test() {
        adminUserDao.findBo(1L);
    }
}
