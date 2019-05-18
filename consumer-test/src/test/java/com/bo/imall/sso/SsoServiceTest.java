package com.bo.imall.sso;

import com.bo.imall.TestApplication;
import com.bo.imall.remote.SsoServiceAdapter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class SsoServiceTest {

    @Autowired
    private SsoServiceAdapter ssoServiceAdapter;

    @Test
    public void test() {
        ssoServiceAdapter.test();
    }
}
