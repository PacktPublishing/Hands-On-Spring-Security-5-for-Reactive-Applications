package com.packtpub.book.ch07.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
                .and()
                .httpBasic(); // Use Basic authentication
        http.cors(); //Enabling CORS
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}admin@password") //{noop} makes sure that the password encoder doesn't do anything
                .roles("USER", "ADMIN") // Role of the user
                .and()
                .withUser("user")
                .password("{noop}user@password")
                .credentialsExpired(true)
                .accountExpired(true)
                .accountLocked(true)
                .roles("USER");
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(new ArrayList<String>(Arrays.asList("*")));
        configuration.setAllowedHeaders(new ArrayList<String>(Arrays.asList("Authorization", "Cache-Control", "Content-Type")));
        configuration.setAllowedMethods(new ArrayList<String>(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH")));
        configuration.setAllowCredentials(true);
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
