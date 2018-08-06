package com.packtpub.book.ch07.springsecurity.dsl;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

public class CustomDSL extends AbstractHttpConfigurer<CustomDSL, HttpSecurity> {
  @Override
  public void init(HttpSecurity builder) throws Exception {
    // Any configurations that you would like to do (say as default) can be configured here
  }

  @Override
  public void configure(HttpSecurity builder) throws Exception {
    // Can add anything specific to your application and this will be honoured
  }
}
