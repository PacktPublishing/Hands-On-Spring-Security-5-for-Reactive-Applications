package com.packtpub.book.ch05.springsecurity.repository;

import com.packtpub.book.ch05.springsecurity.model.Movie;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieRepository {
  public Mono<Movie> getMovieById(Long id);
  public Flux<Movie> listMovies();
  public Mono<Void> saveMovie(Mono<Movie> movie);
  public Mono<Movie> putMovie(Long id, Mono<Movie> movie);
  public Mono<String> deleteMovie(Long id);
}
