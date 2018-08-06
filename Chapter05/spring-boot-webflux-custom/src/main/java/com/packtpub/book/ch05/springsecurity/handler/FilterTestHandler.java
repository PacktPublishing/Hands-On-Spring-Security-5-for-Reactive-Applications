package com.packtpub.book.ch05.springsecurity.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class FilterTestHandler {

    public Mono<ServerResponse> getTestValue(ServerRequest request) {
        Mono<String> pathVariable = Mono.just(request.pathVariable("pathVariable"));
        return ServerResponse.ok().body(pathVariable, String.class);
    }
}