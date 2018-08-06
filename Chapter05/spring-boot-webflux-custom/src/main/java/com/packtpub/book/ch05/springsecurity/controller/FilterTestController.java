package com.packtpub.book.ch05.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FilterTestController {

    @GetMapping(path = "/filtertest1/{pathVariable}")
    public Mono<String> getPathVariable(@PathVariable String pathVariable) {
        return Mono.just(pathVariable);
    }
}