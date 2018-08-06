package com.packtpub.book.ch05.springsecurity;

import com.packtpub.book.ch05.springsecurity.model.Movie;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SampleApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void getAllMovies() {
        System.out.println("Test 1 executing getAllMovies");

        webTestClient
                .mutate()
                .filter(userCredentials())
                .build()
                .get()
                .uri("/api/movie")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Movie.class);
    }

    private ExchangeFilterFunction userCredentials() {
        return ExchangeFilterFunctions.basicAuthentication("user","password");
    }

    private ExchangeFilterFunction adminCredentials() {
        return ExchangeFilterFunctions.basicAuthentication("admin","password");
    }

}
