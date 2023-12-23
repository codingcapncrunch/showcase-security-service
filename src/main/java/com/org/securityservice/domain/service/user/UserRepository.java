package com.org.securityservice.domain.service.user;

import com.org.securityservice.domain.model.User;

public interface UserRepository {

    User loadUserByEmail(String email);

    User addUser(User user);

}
