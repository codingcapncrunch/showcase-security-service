package com.org.securityservice.domain.service.user;

import com.org.securityservice.domain.model.ShowCaseUser;
import com.org.securityservice.domain.model.UserRegion;
import com.org.securityservice.domain.model.UserRole;

public interface ManageUserService {

    ShowCaseUser getUserForLogin(String username);

    ShowCaseUser getUserByUsername(String username);

    ShowCaseUser addRole(UserRole role, String username);

    ShowCaseUser removeRole(UserRole role, String username);

    ShowCaseUser updateRegion(UserRegion region, String username);

    ShowCaseUser updateDisplayName(String newDisplayName, String username);

}
