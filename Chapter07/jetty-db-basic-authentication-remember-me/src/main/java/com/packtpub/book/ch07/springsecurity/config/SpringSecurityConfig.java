package com.packtpub.book.ch07.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.sql.DataSource;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled"
                        + " from users where username = ?")
                .authoritiesByUsernameQuery("select username, authority "
                        + "from authorities where username = ?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable();
        http
            .csrf()
            .csrfTokenRepository(new CookieCsrfTokenRepository());
        //Session creation policy
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
        //Invalid session handling
        http.sessionManagement().invalidSessionUrl("/invalidSession");
        //Session fixation approaches
        http.sessionManagement().sessionFixation().migrateSession();
        // Concurrent sessions controlled to maximum of 1
        http.sessionManagement().maximumSessions(1);

        http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
            .and()
            .authorizeRequests().antMatchers("/login**").permitAll()
            .and()
            .formLogin().loginPage("/login")
                .loginProcessingUrl("/loginProc")
                .failureUrl("/login?error=true")
                .permitAll()
            .and()
            .logout()
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .permitAll()
            .and()
            .rememberMe().rememberMeParameter("rememberme").tokenRepository(tokenRepository());

    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl=new JdbcTokenRepositoryImpl();
        jdbcTokenRepositoryImpl.setDataSource(dataSource);
        return jdbcTokenRepositoryImpl;
    }
}
