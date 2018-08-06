package com.packtpub.book.ch06.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class OAuthClientConfig {

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;

    @Autowired
    @Qualifier("movieAppClientDetails")
    private OAuth2ProtectedResourceDetails movieAppClientDetails;

    @ConfigurationProperties(prefix = "security.oauth2.client.movie-app-client")
    @Bean
    public OAuth2ProtectedResourceDetails movieAppClientDetails() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OAuth2RestTemplate movieAppRestTemplate() {
        return new OAuth2RestTemplate(movieAppClientDetails, oauth2ClientContext);
    }
}
