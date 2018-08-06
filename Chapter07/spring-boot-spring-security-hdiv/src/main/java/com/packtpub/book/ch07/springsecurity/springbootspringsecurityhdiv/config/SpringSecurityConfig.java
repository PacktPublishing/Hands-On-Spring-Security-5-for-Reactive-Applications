package com.packtpub.book.ch07.springsecurity.springbootspringsecurityhdiv.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
      .withUser("admin")
      .password("{noop}password") //{noop} makes sure that the password encoder doesn't do anything
      .roles("USER", "ADMIN") // Role of the user
      .and()
      .withUser("user")
      .password("{noop}password")
      .credentialsExpired(true)
      .accountExpired(true)
      .accountLocked(true)
      .roles("USER");
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/css/**").permitAll();
    http.authorizeRequests().anyRequest().fullyAuthenticated().and()
      .formLogin()
      .loginPage("/login")
      .failureUrl("/login?error").permitAll();
  }
}
