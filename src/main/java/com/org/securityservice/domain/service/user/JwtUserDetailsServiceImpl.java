package com.org.securityservice.domain.service.user;

import com.org.securityservice.api.config.exception.AppException;
import com.org.securityservice.api.config.exception.ExceptionEnum;
import com.org.securityservice.domain.model.ShowCaseUser;
import com.org.securityservice.utils.ExceptionUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Log4j2
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService, JwtUserDetailsService {

    private ManageUserService manageUserService;

    @Autowired
    public JwtUserDetailsServiceImpl(ManageUserService manageUserService) {
        this.manageUserService = manageUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        log.debug("At JwtUserDetailsServiceImpl.loadUserByUsername for username: {}", username);
        if (!StringUtils.isEmpty(username)) {
            ShowCaseUser showCaseUser = this.manageUserService.getUserForLogin(username);
            if (showCaseUser == null) {
                ExceptionUtil.throwException(new AppException(ExceptionEnum.USER1001, "username: " + username));
            }
            log.debug("At JwtUserDetailsServiceImpl.loadUserByUsername returning userDetails {}", showCaseUser.toString());
            return showCaseUser;
        }
        ExceptionUtil.throwException(new AppException(ExceptionEnum.USER1001));
        return null;
    }
}
