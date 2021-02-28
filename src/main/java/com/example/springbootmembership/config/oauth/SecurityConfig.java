package com.example.springbootmembership.config.oauth;

import com.example.springbootmembership.domain.user.Role;
import com.example.springbootmembership.service.oauth.CustomOAuth2UserService;
import com.example.springbootmembership.service.security.AuthenticationProviderImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired private CustomOAuth2UserService customOAuth2UserService;
    @Autowired private UserDetailsService userDetailsService;
    @Autowired private AuthenticationProvider authenticationProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/css/**", "/images/**", "/js/**").permitAll()
                    .antMatchers("/", "/user/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                    .and()
                .logout()
                    .logoutSuccessUrl("/")
                    .and()
                .oauth2Login()
                    .userInfoEndpoint()
                        .userService(customOAuth2UserService)
                        .and()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/user/loginProcess")
                    .defaultSuccessUrl("/")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .permitAll()
                    .and()
                .authenticationProvider(authenticationProvider);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
            userDetailsService(userDetailsService).
            passwordEncoder(passwordEncoder());
    }
}
