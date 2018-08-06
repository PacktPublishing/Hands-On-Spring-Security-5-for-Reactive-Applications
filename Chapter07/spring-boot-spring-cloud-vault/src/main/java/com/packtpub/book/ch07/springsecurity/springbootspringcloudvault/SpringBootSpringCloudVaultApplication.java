package com.packtpub.book.ch07.springsecurity.springbootspringcloudvault;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SpringBootSpringCloudVaultApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSpringCloudVaultApplication.class, args);
	}

	@Value("${password}")
	String password;

	@PostConstruct
	private void postConstruct() {
		System.out.println("Secret in Movie application password is: " + password);
	}


}
