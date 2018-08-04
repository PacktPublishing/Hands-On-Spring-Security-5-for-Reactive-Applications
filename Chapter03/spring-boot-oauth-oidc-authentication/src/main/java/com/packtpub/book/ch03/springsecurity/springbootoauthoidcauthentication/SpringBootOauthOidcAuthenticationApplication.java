package com.packtpub.book.ch03.springsecurity.springbootoauthoidcauthentication;

import com.packtpub.book.ch03.springsecurity.springbootoauthoidcauthentication.controller.HomeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses=HomeController.class)
public class SpringBootOauthOidcAuthenticationApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootOauthOidcAuthenticationApplication.class, args);
	}
}
