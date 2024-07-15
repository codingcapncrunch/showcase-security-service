package com.org.securityservice.domain.model.mockentity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MockUserSecurity {

    private String id;
    private String password;
    private boolean isAccountLocked;
    private boolean isAccountExpired;
    private boolean isCredentialsExpired;
    private boolean isEnabled;

}
