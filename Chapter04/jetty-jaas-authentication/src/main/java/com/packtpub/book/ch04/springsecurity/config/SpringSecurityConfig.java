package com.packtpub.book.ch04.springsecurity.config;

import com.packtpub.book.ch04.springsecurity.loginmodule.JaasAuthorityGranter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.authentication.jaas.DefaultJaasAuthenticationProvider;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.security.auth.login.AppConfigurationEntry;
import java.util.HashMap;

/**
 * Spring Security related configurations
 */
@Configuration
@EnableWebSecurity
@ComponentScan( basePackageClasses = JaasAuthorityGranter.class)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    //Class which we use to grant users necessary roles in our sample
    @Autowired
    JaasAuthorityGranter jaasAuthorityGranter = new JaasAuthorityGranter();

    @Bean
    DefaultJaasAuthenticationProvider jaasAuthenticationProvider() {
        AppConfigurationEntry appConfig = new AppConfigurationEntry("com.packtpub.book.ch04.springsecurity.loginmodule.JaasLoginModule",
                AppConfigurationEntry.LoginModuleControlFlag.REQUIRED, new HashMap());

        InMemoryConfiguration memoryConfig = new InMemoryConfiguration(new AppConfigurationEntry[] { appConfig });

        DefaultJaasAuthenticationProvider def = new DefaultJaasAuthenticationProvider();
        def.setConfiguration(memoryConfig);
        def.setAuthorityGranters(new AuthorityGranter[] {jaasAuthorityGranter});
        return def;
    }

    //We are configuring jaasAuthenticationProvider as our global AuthenticationProvider
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(jaasAuthenticationProvider());
    }

    // Setting up our HTTP security
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Setting up security
        http.authorizeRequests()
                .regexMatchers("/admin/.*").hasRole("ADMIN")
                .anyRequest().authenticated().and().httpBasic();

        // Setting our login page and to make it public
        http.formLogin().loginPage("/login").permitAll();
        // Logout configuration
        http.logout().logoutSuccessUrl("/");
        // Exception handling, for access denied
        http.exceptionHandling().accessDeniedPage("/noaccess");
    }
}
