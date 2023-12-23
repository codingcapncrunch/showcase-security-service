package com.org.securityservice.domain.service.user;

import com.org.securityservice.domain.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    UserDetailsService userDetailsService();

    UserDetails registerNewUser(User user);

    UserDetails loadUserDetailByEmail(String email);

}
