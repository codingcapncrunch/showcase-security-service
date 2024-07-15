package com.org.securityservice.domain.service.user;

import com.org.securityservice.domain.model.mockentity.MockUserEntity;
import com.org.securityservice.domain.model.mockentity.MockUserSecurity;

public interface UserSecurityRepository {



    MockUserSecurity getUserSecurity(String id);

    MockUserSecurity addUserSecurity(MockUserEntity mockUser);

    MockUserSecurity updateUserSecurity(MockUserSecurity mockUserSecurity);

    void deleteMockUserSecurity(String id);
}
