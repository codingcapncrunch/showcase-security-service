package com.org.securityservice.domain.service.user;

import com.org.securityservice.api.config.exception.AppException;
import com.org.securityservice.api.config.exception.ExceptionEnum;
import com.org.securityservice.domain.model.UserRegion;
import com.org.securityservice.domain.model.UserRole;
import com.org.securityservice.domain.model.mockentity.MockUserEntity;
import com.org.securityservice.utils.ExceptionUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Log4j2
@Service
public class UserRepositoryImpl implements UserRepository {

    private Map<String, MockUserEntity> userMap;

    public UserRepositoryImpl() {

        this.userMap = new HashMap<>();
        String id = UUID.randomUUID().toString();
        Set<UserRole> roles = new HashSet<>();
        roles.add(UserRole.ROLE_USER);
        roles.add(UserRole.ROLE_ADMIN);
        userMap.put(id, new MockUserEntity(id, "admin@gmail.com", UserRegion.US_EAST, "admin", roles));

        id = UUID.randomUUID().toString();
        roles.clear();
        roles.add(UserRole.ROLE_USER);
        userMap.put(id, new MockUserEntity(id, "user1@gmail.com", UserRegion.US_CENTRAL, "user1", roles));

        id = UUID.randomUUID().toString();
        roles.clear();
        roles.add(UserRole.ROLE_USER);
        userMap.put(id, new MockUserEntity(id, "user2@gmail.com", UserRegion.US_CENTRAL, "user2", roles));

        id = UUID.randomUUID().toString();
        roles.clear();
        roles.add(UserRole.ROLE_USER);
        roles.add(UserRole.ROLE_MANAGER);
        userMap.put(id, new MockUserEntity(id, "manager@gmail.com", UserRegion.US_EAST, "manager", roles));

    }

    @Override
    public Collection<MockUserEntity> getAllUsers() {
        return new HashSet<>(this.userMap.values());
    }

    @Override
    public MockUserEntity getUserByUsername(String username) {
        if (!StringUtils.isEmpty(username)){
            for (MockUserEntity user : this.userMap.values()){
                if(user.getUsername().equalsIgnoreCase(username)){
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public MockUserEntity getUserById(String id) {
        return this.userMap.get(id);
    }

    @Override
    public void deleteUserByUserId(String id) {
        if (StringUtils.isEmpty(id)){
            ExceptionUtil.throwException(new AppException(ExceptionEnum.USER1000, "provided id is null"));
        }
        if (this.userMap.get(id)==null){
            ExceptionUtil.throwException(new AppException(ExceptionEnum.USER1000, "provided id: "+id));
        }
        this.userMap.remove(id);
    }

    @Override
    public MockUserEntity addUser(MockUserEntity user) {
        this.validateUser(user);
        String id = UUID.randomUUID().toString();
        userMap.put(id, new MockUserEntity(id, user.getUsername(), user.getRegion(), user.getDisplayName(), user.getRoles()));
        return user;
    }

    @Override
    public MockUserEntity updateUser(MockUserEntity user) {
        log.warn("UserRepositoryImpl.updateUser() NOT yet implemented");
        return null;
    }

    private void validateUser(MockUserEntity user){
        if (user == null){
            log.error("Invalid user object - is null");
            ExceptionUtil.throwException(new AppException(ExceptionEnum.USER1004));
        } else if (StringUtils.isEmpty(user.getUsername()) || user.getRegion() == null || user.getRoles() == null || user.getRoles().isEmpty()) {
            log.error("Invalid user object: {}", user.toString());
            ExceptionUtil.throwException(new AppException(ExceptionEnum.USER1004));
        }
    }
}
