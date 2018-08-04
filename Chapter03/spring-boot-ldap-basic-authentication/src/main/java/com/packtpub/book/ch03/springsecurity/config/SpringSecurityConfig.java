package com.packtpub.book.ch03.springsecurity.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(SpringSecurityConfig.class);
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()    .antMatchers("/admins").hasRole("ADMINS")
                .antMatchers("/users").hasRole("USERS")
                .anyRequest().fullyAuthenticated()
                .and()
                .httpBasic(); // Use Basic authentication
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .userSearchBase("ou=people")
                .userSearchFilter("uid={0}")
                .groupSearchBase("ou=groups")
                .groupSearchFilter("uniqueMember={0}")
                .contextSource(contextSource())
                .passwordCompare()
                .passwordAttribute("userPassword");
    }

    @Bean
    public DefaultSpringSecurityContextSource contextSource() {
        LOG.info("Inside configuring embedded LDAP server");
        DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource(
                Arrays.asList("ldap://localhost:8389/"), "dc=packtpub,dc=com");
        contextSource.afterPropertiesSet();
        return contextSource;
    }

}
