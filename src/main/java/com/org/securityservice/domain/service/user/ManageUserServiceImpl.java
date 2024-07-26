package com.org.securityservice.domain.service.user;

import com.org.securityservice.api.config.exception.AppException;
import com.org.securityservice.api.config.exception.ExceptionEnum;
import com.org.securityservice.domain.model.ShowCaseUser;
import com.org.securityservice.domain.model.UserRegion;
import com.org.securityservice.domain.model.UserRole;
import com.org.securityservice.domain.model.mockentity.MockUserEntity;
import com.org.securityservice.domain.model.mockentity.MockUserSecurity;
import com.org.securityservice.utils.ExceptionUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

@Log4j2
@Service
public class ManageUserServiceImpl implements ManageUserService {

    private UserRepository userRepository;
    private UserSecurityRepository userSecurityRepository;

    @Autowired
    public ManageUserServiceImpl(UserRepository userRepository, UserSecurityRepository userSecurityRepository) {
        this.userRepository = userRepository;
        this.userSecurityRepository = userSecurityRepository;
    }

    //hydrate mock user security data
    @PostConstruct
    private void loadUserSecurityDetails(){
        for (MockUserEntity mockUser : this.userRepository.getAllUsers()){
            this.userSecurityRepository.addUserSecurity(mockUser);
        }
    }

    @Override
    public ShowCaseUser getUserForLogin(String username) {
        if (!StringUtils.isEmpty(username)){
            for (MockUserEntity user : this.userRepository.getAllUsers()){
                if (user.getUsername().equalsIgnoreCase(username)){
                    MockUserSecurity userSecurity = this.userSecurityRepository.getUserSecurity(user.getId());
                    if (userSecurity!=null){
                        return new ShowCaseUser(user.getId(), user.getUsername(), userSecurity.getPassword(), user.getRoles(), userSecurity.isAccountLocked(), userSecurity.isAccountExpired(),
                                userSecurity.isCredentialsExpired(), userSecurity.isEnabled());
                    } else {
                        log.error("User security object not found for username: {}", username);
                        ExceptionUtil.throwException(new AppException(ExceptionEnum.USER1007));
                    }
                }
            }
        } else {
            log.error("Invalid username - null at getUserForLogin");
            ExceptionUtil.throwException(new AppException(ExceptionEnum.USER1006));
        }
        return null;
    }

    @Override
    public ShowCaseUser getUserByUsername(String username) {
        if (!StringUtils.isEmpty(username)){
            for (MockUserEntity user : this.userRepository.getAllUsers()){
                if (user.getUsername().equalsIgnoreCase(username)){
                    //user match
                    MockUserSecurity userSecurity = this.userSecurityRepository.getUserSecurity(user.getId());
                    if (userSecurity!=null){
                        return new ShowCaseUser(user.getId(), user.getUsername(), user.getDisplayName(), user.getRegion(), user.getRoles(),
                                userSecurity.isAccountLocked(), userSecurity.isAccountExpired(), userSecurity.isCredentialsExpired(), userSecurity.isEnabled());
                    } else {
                        log.error("User security object not found for username: {}", username);
                        ExceptionUtil.throwException(new AppException(ExceptionEnum.USER1007));
                    }
                }
            }
        } else {
            log.error("Invalid username - null at getUserByUsername");
            ExceptionUtil.throwException(new AppException(ExceptionEnum.USER1006));
        }
        return null;
    }

    @Override
    public ShowCaseUser addRole(UserRole role, String username) {

        if (!StringUtils.isEmpty(username) && role!=null){
            MockUserEntity userEntity = this.userRepository.getUserByUsername(username);
            if (userEntity!=null){
                userEntity.getRoles().add(role);
                MockUserEntity updatedUserEntity = this.userRepository.updateUser(userEntity);
                return this.translateMockUserToShowCaseUser(updatedUserEntity);

            } else {
                log.error("Null user for username '{}' for addRole request", username);
                ExceptionUtil.throwException(new AppException(ExceptionEnum.USER1001));
            }
            return null;
        } else {
            ExceptionUtil.throwException(new AppException(ExceptionEnum.MNGUSER1001));
        }
        return null;
    }

    @Override
    public ShowCaseUser removeRole(UserRole role, String username) {
        return null;
    }

    @Override
    public ShowCaseUser updateRegion(UserRegion region, String username) {
        return null;
    }

    @Override
    public ShowCaseUser updateDisplayName(String newDisplayName, String username) {
        return null;
    }

    private ShowCaseUser translateMockUserToShowCaseUser(MockUserEntity user){
        if (user!=null){
            MockUserSecurity userSecurity = this.userSecurityRepository.getUserSecurity(user.getId());
            return new ShowCaseUser(user.getId(), user.getUsername(), user.getDisplayName(), user.getRegion(), user.getRoles(),
                    userSecurity.isAccountLocked(), userSecurity.isAccountExpired(), userSecurity.isCredentialsExpired(), userSecurity.isEnabled());
        } else {
             log.error("Unexpected error at ManageUserServiceImpl.translateMockUserToShowCaseUser - null user");
             return null;
        }
    }
}
