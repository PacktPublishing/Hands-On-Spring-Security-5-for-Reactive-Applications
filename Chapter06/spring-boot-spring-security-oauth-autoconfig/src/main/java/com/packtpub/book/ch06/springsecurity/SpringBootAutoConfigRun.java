package com.packtpub.book.ch06.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@RestController
public class SpringBootAutoConfigRun extends AuthorizationServerConfigurerAdapter {

  @GetMapping("/user")
  public Principal user(Principal user) {
    return user;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringBootAutoConfigRun.class, args);
  }

}
