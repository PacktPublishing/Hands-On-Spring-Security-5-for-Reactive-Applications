package com.packtpub.book.ch02.springsecurity.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
                .and()
                .httpBasic(); // Use Basic authentication
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}admin@password") //{noop} makes sure that the password encoder doesn't do anything
                .roles("ADMIN") // Role of the user
                .and()
                .withUser("user")
                .password("{noop}user@password")
                .credentialsExpired(true)
                .accountExpired(true)
                .accountLocked(true)
                .roles("USER");
    }

}
