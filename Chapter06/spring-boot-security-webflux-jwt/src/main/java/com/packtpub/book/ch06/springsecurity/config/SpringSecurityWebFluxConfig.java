package com.packtpub.book.ch06.springsecurity.config;

import com.packtpub.book.ch06.springsecurity.auth.JWTAuthSuccessHandler;
import com.packtpub.book.ch06.springsecurity.auth.JWTAuthWebFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SpringSecurityWebFluxConfig {

  private static final String[] WHITELISTED_AUTH_URLS = {
    "/login",
    "/",
    "/auth/**"
  };

  /**
   * The test defined in SampleApplicationTests class will only get executed
   * if you change the authentication mechanism to basic (from form mechanism)
   * in SpringSecurityWebFluxConfig file
   * @param http
   * @return
   * @throws Exception
   */
  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {

    AuthenticationWebFilter authenticationJWT = new AuthenticationWebFilter(new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsRepository()));
    authenticationJWT.setAuthenticationSuccessHandler(new JWTAuthSuccessHandler());

    http.csrf().disable();

    http
      .authorizeExchange()
      .pathMatchers(WHITELISTED_AUTH_URLS)
      .permitAll()
      .and()
      .addFilterAt(authenticationJWT, SecurityWebFiltersOrder.FIRST)
      .authorizeExchange()
      .pathMatchers(HttpMethod.GET, "/api/movie/**").hasRole("USER")
      .pathMatchers(HttpMethod.POST, "/api/movie/**").hasRole("ADMIN")
      .anyExchange().authenticated()
      .and()
      .addFilterAt(new JWTAuthWebFilter(), SecurityWebFiltersOrder.HTTP_BASIC);

    return http.build();
  }

  @Bean
  public MapReactiveUserDetailsService userDetailsRepository() {
    UserDetails user = User.withUsername("user").password("{noop}password").roles("USER").build();
    UserDetails admin = User.withUsername("admin").password("{noop}password").roles("USER","ADMIN").build();
    return new MapReactiveUserDetailsService(user, admin);
  }

}
