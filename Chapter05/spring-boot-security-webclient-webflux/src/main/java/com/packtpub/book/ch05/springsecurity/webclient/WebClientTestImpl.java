package com.packtpub.book.ch05.springsecurity.webclient;

import com.packtpub.book.ch05.springsecurity.exception.SampleException;
import com.packtpub.book.ch05.springsecurity.model.Movie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WebClientTestImpl implements WebClientTestInterface {

    private final WebClient webClient;

    public WebClientTestImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl("http://localhost:8080/api/movie").build();
    }

    @Override
    public Mono<Movie> getMovieById(Long id) {
        return this.webClient.get().uri("/{id}", id)
                .retrieve().bodyToMono(Movie.class);
    }

    @Override
    public Flux<Movie> listMovies() {
        return webClient.get().uri("/")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                        Mono.error(new SampleException())
                )
                .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                        Mono.error(new SampleException())
                )
                .bodyToFlux(Movie.class);
    }

    @Override
    public Mono<Movie> saveMovie(Movie movie) {
        return webClient.post().uri("/")
                .body(BodyInserters.fromObject(movie))
                .exchange().flatMap( clientResponse -> clientResponse.bodyToMono(Movie.class) );
    }

    @Override
    public Mono<Movie> putMovie(Long id, Movie movie) {
        return webClient.put().uri("/{id}", id)
                .body(BodyInserters.fromObject(movie))
                .exchange().flatMap(clientResponse -> clientResponse.bodyToMono(Movie.class));
    }

    @Override
    public Mono<Void> deleteMovie(Long id) {
        return webClient.delete().uri("/{id}", id)
                .retrieve().bodyToMono(Void.class);
    }
}
