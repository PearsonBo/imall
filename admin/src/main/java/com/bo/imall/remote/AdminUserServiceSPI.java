package com.bo.imall.remote;

import com.bo.imall.model.admin.AdminUser;

public interface AdminUserServiceSPI {

    void register(AdminUser adminUser);

    void login(AdminUser adminUser);

    void logout(AdminUser adminUser);

    void changePassword(AdminUser adminUser);
}
