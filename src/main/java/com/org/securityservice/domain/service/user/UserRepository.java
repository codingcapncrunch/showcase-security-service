package com.org.securityservice.domain.service.user;

import com.org.securityservice.domain.model.ShowCaseUser;

public interface UserRepository {

    ShowCaseUser getUserByUsername(String email);

    ShowCaseUser getUserById(String id);

    void deleteUserByUserId(String id);

    ShowCaseUser addUser(ShowCaseUser user);

    ShowCaseUser updateUser(ShowCaseUser user);

}
