package com.org.securityservice.domain.service.user;

import com.org.securityservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {

        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {

                UserDetails user = userRepository.loadUserByEmail(username);
                if (user == null){
                    throw new UsernameNotFoundException("User not found");
                } else {
                    return user;
                }
            }

        };

    }

    @Override
    public User loadUserDetailByEmail(String email) {
        return this.userRepository.loadUserByEmail(email);
    }

    @Override
    public User registerNewUser(User user) {
        this.userRepository.addUser(user);

        return null;
    }


}
