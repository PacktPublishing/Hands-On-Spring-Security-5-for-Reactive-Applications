package com.packtpub.book.ch07.springsecurity.springbootspringsecurityhdiv.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages= {"com.packtpub.book.ch07.springsecurity"})
public class WebApplicationConfig implements WebMvcConfigurer {
  @Override
  public void addViewControllers(final ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("login");
  }
}
