package com.packtpub.book.ch05.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@Configuration
@EnableReactiveMethodSecurity
public class SpringSecurityWebFluxConfig {

  @Bean
  SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
    return http
      .csrf().disable()//Just for testing purpose
      .authorizeExchange()
      .pathMatchers(HttpMethod.GET, "/movies/**").permitAll()
      .pathMatchers(HttpMethod.POST, "/movies/**").permitAll()
      .pathMatchers(HttpMethod.GET, "/api/movie/**").hasRole("USER")
      .pathMatchers(HttpMethod.POST, "/api/movie/**").hasRole("ADMIN")
      .anyExchange().authenticated()
      .and().httpBasic()
      .and().build();
  }

  @Bean
  public MapReactiveUserDetailsService userDetailsRepository() {
    UserDetails user = User.withUsername("user").password("{noop}password").roles("USER").build();
    UserDetails admin = User.withUsername("admin").password("{noop}password").roles("USER","ADMIN").build();
    return new MapReactiveUserDetailsService(user, admin);
  }

}
