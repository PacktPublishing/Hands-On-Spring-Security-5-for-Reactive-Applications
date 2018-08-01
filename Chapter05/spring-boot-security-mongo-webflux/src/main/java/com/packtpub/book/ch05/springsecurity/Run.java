package com.packtpub.book.ch05.springsecurity;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class Run {

    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }

}
