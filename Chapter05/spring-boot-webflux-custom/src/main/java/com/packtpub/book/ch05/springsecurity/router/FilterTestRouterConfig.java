package com.packtpub.book.ch05.springsecurity.router;

import com.packtpub.book.ch05.springsecurity.filter.SampleHandlerFilterFunction;
import com.packtpub.book.ch05.springsecurity.handler.FilterTestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class FilterTestRouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routerFunction2(FilterTestHandler filterTestHandler) {
        return route(RequestPredicates.GET("/filtertest2/{pathVariable}"), filterTestHandler::getTestValue)
                .filter(new SampleHandlerFilterFunction());
    }
}