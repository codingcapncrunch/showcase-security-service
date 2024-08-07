package com.org.securityservice.api.filter;

import com.org.securityservice.api.config.exception.AppException;
import com.org.securityservice.api.config.exception.ExceptionEnum;
import com.org.securityservice.domain.service.token.TokenService;
import com.org.securityservice.domain.service.user.JwtUserDetailsService;
import com.org.securityservice.utils.ExceptionUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
public class JwtFilter extends OncePerRequestFilter {

    private HandlerExceptionResolver resolver;
    private JwtUserDetailsService jwtUserDetailsService;
    private TokenService tokenService;

    public JwtFilter(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver, JwtUserDetailsService jwtUserDetailsService, TokenService tokenService) {
        this.resolver = resolver;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try{
            String tokenHeader = request.getHeader("Authorization");
            String username = null;
            String token = null;

            if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
                token = tokenHeader.substring(7);
                try {
                    username = tokenService.getUsernameFromToken(token);
                } catch (IllegalArgumentException e) {
                    log.error("Unable to get JWT Token");
                    ExceptionUtil.throwException(new AppException(ExceptionEnum.JWT1004));
                } catch (ExpiredJwtException e) {
                    log.error("JWT Token has expired");
                    ExceptionUtil.throwException(new AppException(ExceptionEnum.JWT1003));
                } catch (Exception e){
                    log.error("JWT Exception");
                    ExceptionUtil.throwException(new AppException(ExceptionEnum.JWT1002));
                }
            } else {
                log.debug("Bearer token not found");
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
                if (tokenService.validateJwtToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken
                            authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null,
                            userDetails.getAuthorities());
                    authenticationToken.setDetails(new
                            WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e){
            resolver.resolveException(request, response, null, e);
        }
    }
}
