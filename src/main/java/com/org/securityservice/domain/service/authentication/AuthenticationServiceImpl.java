package com.org.securityservice.domain.service.authentication;


import com.org.securityservice.domain.model.AuthenticateRequest;
import com.org.securityservice.domain.model.AuthenticationResponse;
import com.org.securityservice.domain.model.TokenRequest;
import com.org.securityservice.domain.service.token.TokenService;
import com.org.securityservice.domain.service.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final TokenService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse authenticate(AuthenticateRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.loadUserByEmail(request.getEmail());
        if (user == null) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.setUsername(user.getEmail());
        tokenRequest.addClaim("role", user.getRole().toString());

        var jwt = jwtService.generateToken(tokenRequest);
        return AuthenticationResponse.builder().token(jwt).build();
    }
}
