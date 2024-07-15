package com.org.securityservice.utils;

import com.org.securityservice.api.config.exception.AppException;
import com.org.securityservice.api.config.exception.ExceptionEnum;
import com.org.securityservice.domain.model.UserRole;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Log4j2
@Component
public class UserRoleHelperUtil {

    public UserRole validateAndConvertToUserRole(String role){

        try{
            if (!StringUtils.isEmpty(role)){
                return UserRole.valueOf(role);
            } else {
                ExceptionUtil.throwException(new AppException(ExceptionEnum.USERROLE1000, "provided role is invalid: null"));
            }
        } catch (Exception ex){
            log.error("Unknown exception at validateAndConvertToUserRole ex message: {}", ex.getMessage());
            ExceptionUtil.throwException(new AppException(ExceptionEnum.USERROLE1000, "Unknown exception at role conversion helper"));
        }
        return null;
    }
}
