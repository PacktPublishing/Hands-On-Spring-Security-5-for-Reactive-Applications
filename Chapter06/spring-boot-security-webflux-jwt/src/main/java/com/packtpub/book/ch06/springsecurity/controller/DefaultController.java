package com.packtpub.book.ch06.springsecurity.controller;

import org.apache.logging.log4j.message.FormattedMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class DefaultController {

  @GetMapping("/")
  public Flux<FormattedMessage> guestHome() {
    return Flux.just(new FormattedMessage("Welcome Guest"));
  }

  @GetMapping("/login")
  public Flux<FormattedMessage> login() {
    return Flux.just(new FormattedMessage("Welcome Guest, its Basic Authentication"));
  }
}
