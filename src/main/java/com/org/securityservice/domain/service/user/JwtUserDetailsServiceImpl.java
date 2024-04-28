package com.org.securityservice.domain.service.user;

import com.org.securityservice.api.config.exception.AppException;
import com.org.securityservice.api.config.exception.ExceptionEnum;
import com.org.securityservice.utils.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

@Log4j2
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService, JwtUserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public JwtUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        log.debug("At JwtUserDetailsServiceImpl.loadUserByUsername for username: {}", username);
        if (!StringUtils.isEmpty(username)) {
            UserDetails userDetails = this.userRepository.getUserByUsername(username);
            if (userDetails == null) {
                Utils.throwException(new AppException(ExceptionEnum.USER1001, "username: " + username));
            }
            log.debug("At JwtUserDetailsServiceImpl.loadUserByUsername returning userDetails {}", userDetails.toString());
            return userDetails;
        }
        Utils.throwException(new AppException(ExceptionEnum.USER1001));
        return null;

//        if ("randomuser123".equals(username)) {
//            return new User("randomuser123", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }

    }
}
