package com.packtpub.book.ch07.springsecurity.springbootspringsecurityhdiv.config;

import org.hdiv.config.annotation.EnableHdivWebSecurity;
import org.hdiv.config.annotation.ExclusionRegistry;
import org.hdiv.config.annotation.configuration.HdivWebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHdivWebSecurity
public class HdivSecurityConfig extends HdivWebSecurityConfigurerAdapter {
  @Override
  public void addExclusions(final ExclusionRegistry registry) {
    registry.addUrlExclusions("/login");
  }
}
