package com.org.securityservice.domain.service.user;

import com.org.securityservice.domain.model.Role;
import com.org.securityservice.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserRepositoryImpl implements UserRepository {

    private HashMap<String, User> usersMap;

    public UserRepositoryImpl() {

        HashMap<String, User> newUsersMap = new HashMap<>();

        User generalDetails = new User("generalRoleId", "generalFirstName", "lastName", "user@email.com", "generalpassword", Role.GENERAL);
        newUsersMap.put("user@email.com", generalDetails);

        User adminDetails = new User("adminRoleId", "adminFirstName", "lastName", "admin@email.com", "adminpassword", Role.ADMIN);
        newUsersMap.put("admin@email.com", adminDetails);

        this.usersMap = newUsersMap;
    }

    @Override
    public User loadUserByEmail(String email) {
        return usersMap.get(email);
    }

    @Override
    public User addUser(User user){
        this.usersMap.put(user.getEmail(), user);
        return user;
    }

}
