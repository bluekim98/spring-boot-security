package com.example.springbootmembership.service.security;

import com.example.springbootmembership.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationProviderImpl implements AuthenticationProvider {

    @Autowired private  UserDetailsService userDetailsService;
    @Autowired private  PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String)authentication.getCredentials();
        String password = (String)authentication.getCredentials();
        User user = (User) userDetailsService.loadUserByUsername(username);
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException(username);
        }
        if(!user.isEnabled()) {
            throw new BadCredentialsException(username);
        }
        return  new UsernamePasswordAuthenticationToken(username, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
