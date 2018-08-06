package com.packtpub.book.ch05.springsecurity.webclient;

import com.packtpub.book.ch05.springsecurity.model.Movie;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WebClientTestInterface {
    public Mono<Movie> getMovieById(Long id);
    public Flux<Movie> listMovies();
    public Mono<Movie> saveMovie(Movie movie);
    public Mono<Movie> putMovie(Long id, Movie movie);
    public Mono<Void> deleteMovie(Long id);
}
