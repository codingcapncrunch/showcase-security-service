package com.org.securityservice.domain.service.user;

import com.org.securityservice.api.config.exception.AppException;
import com.org.securityservice.api.config.exception.ExceptionEnum;
import com.org.securityservice.domain.model.mockentity.MockUserEntity;
import com.org.securityservice.domain.model.mockentity.MockUserSecurity;
import com.org.securityservice.utils.ExceptionUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class UserSecurityRepositoryImpl implements UserSecurityRepository{

    private Map<String, MockUserSecurity> mockUserSecurityMap;

    public UserSecurityRepositoryImpl() {

        this.mockUserSecurityMap = new HashMap<>();

    }

    @Override
    public MockUserSecurity getUserSecurity(String id) {
        for (MockUserSecurity userSecurity : this.mockUserSecurityMap.values()){
            if (userSecurity.getId().equalsIgnoreCase(id)){
                return userSecurity;
            }
        }
        log.warn("Unexpected - getUserSecurity found no match");
        return null;
    }

    @Override
    public MockUserSecurity addUserSecurity(MockUserEntity mockUser) {
        this.validateUser(mockUser);

        MockUserSecurity mockUserSecurity = new MockUserSecurity(mockUser.getId(), "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", false, false, false, true);
        this.mockUserSecurityMap.put(mockUser.getId(), mockUserSecurity);
        return mockUserSecurity;
    }

    @Override
    public MockUserSecurity updateUserSecurity(MockUserSecurity mockUserSecurity) {
        return null;
    }

    @Override
    public void deleteMockUserSecurity(String id) {

    }

    private void validateUser(MockUserEntity user){
        if (user == null){
            log.error("Invalid user object - is null");
            ExceptionUtil.throwException(new AppException(ExceptionEnum.USER1005));
        } else if (StringUtils.isEmpty(user.getId())) {
            log.error("Invalid user object - ID is null");
            ExceptionUtil.throwException(new AppException(ExceptionEnum.USER1005));
        }
    }
}
