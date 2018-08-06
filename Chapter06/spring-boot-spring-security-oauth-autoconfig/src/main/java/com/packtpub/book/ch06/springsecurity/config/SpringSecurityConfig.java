package com.packtpub.book.ch06.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfig {
  @Bean
  UserDetailsService userDetailsService() {
    UserDetails defaultUser = User.builder()
      .username("user")
      .password("password")
      .roles("USER")
      .build();
    return new InMemoryUserDetailsManager(defaultUser);
  }
}
