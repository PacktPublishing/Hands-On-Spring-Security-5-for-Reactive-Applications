package com.packtpub.book.ch06.springsecurity.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan({"com.packtpub.book.ch05.springsecurity.controller"})
public class SpringMVCWebConfig implements WebMvcConfigurer {
    //
}
