package com.org.securityservice.domain.service.user;

import com.org.securityservice.api.config.exception.AppException;
import com.org.securityservice.api.config.exception.ExceptionEnum;
import com.org.securityservice.domain.model.ShowCaseUser;
import com.org.securityservice.domain.model.UserRegion;
import com.org.securityservice.utils.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Log4j2
@Service
public class UserRepositoryImpl implements UserRepository {

    private Map<String, ShowCaseUser> userMap;

    public UserRepositoryImpl() {
        Map<String, ShowCaseUser> defaultUserMap = new HashMap<>();
        for (int i=1; i<=4; i++){
            String id = UUID.randomUUID().toString();
            defaultUserMap.put(id, new ShowCaseUser("test"+String.valueOf(i)+"@gmail.com", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", new ArrayList<>(), id, "displayName", UserRegion.US_CENTRAL));
        }
        this.userMap = defaultUserMap;
    }

    @Override
    public ShowCaseUser getUserByUsername(String username) {
        if (!StringUtils.isEmpty(username)){
            for (ShowCaseUser user : this.userMap.values()){
                if(user.getUsername().equalsIgnoreCase(username)){
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public ShowCaseUser getUserById(String id) {
        return this.userMap.get(id);
    }

    @Override
    public void deleteUserByUserId(String id) {
        if (StringUtils.isEmpty(id)){
            Utils.throwException(new AppException(ExceptionEnum.USER1000, "provided id is null"));
        }
        if (this.userMap.get(id)==null){
            Utils.throwException(new AppException(ExceptionEnum.USER1000, "provided id: "+id));
        }
        this.userMap.remove(id);
    }

    @Override
    public ShowCaseUser addUser(ShowCaseUser user) {
        log.warn("UserRepositoryImpl.addUser() NOT yet implemented");
        return null;
    }

    @Override
    public ShowCaseUser updateUser(ShowCaseUser user) {
        log.warn("UserRepositoryImpl.updateUser() NOT yet implemented");
        return null;
    }
}
