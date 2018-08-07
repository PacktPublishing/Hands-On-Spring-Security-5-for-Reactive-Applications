package com.packtpub.book.ch05.springsecurity.handler;

import com.packtpub.book.ch05.springsecurity.model.Movie;
import com.packtpub.book.ch05.springsecurity.repository.MovieRepository;
import com.packtpub.book.ch05.springsecurity.repository.ReactiveMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class MovieHandler {

  @Autowired
  private final MovieRepository movieRepository;

  @Autowired
  private final ReactiveMovieRepository reactiveMovieRepository;

  public MovieHandler(MovieRepository movieRepository, ReactiveMovieRepository reactiveMovieRepository) {
    this.movieRepository = movieRepository;
    this.reactiveMovieRepository = reactiveMovieRepository;
  }

  /**
   * GET ALL Movies
   */
  public Mono<ServerResponse> listMovies(ServerRequest request) {
    // fetch all Movies from repository
    Flux<Movie> movies = movieRepository.listMovies();

    // build response
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(movies, Movie.class);
  }

  /**
   * GET ALL Movies
   */
  public Mono<ServerResponse> findByGenre(ServerRequest request) {

    request.pathVariable("genre");

    // fetch all Movies from repository
    Flux<Movie> movies = reactiveMovieRepository.findAll();

    // build response
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(movies, Movie.class);
  }

  /**
   * GET a Movie by ID
   */
  public Mono<ServerResponse> getMovieById(ServerRequest request) {
    // parse path-variable
    long movieId = Long.valueOf(request.pathVariable("id"));

    // build notFound response
    Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    // get Movie from repository
    Mono<Movie> movieMono = movieRepository.getMovieById(movieId);

    // build response
    return movieMono
      .flatMap(Movie -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(Movie)))
      .switchIfEmpty(notFound);
  }

  /**
   * POST a Movie
   */
  public Mono<ServerResponse> saveMovie(ServerRequest request) {
    Mono<Movie> movieMono = request.bodyToMono(Movie.class);
    return ServerResponse.ok().build(movieRepository.saveMovie(movieMono));
  }

  /**
   *	PUT a Movie
   */
  public Mono<ServerResponse> putMovie(ServerRequest request) {
    // parse id from path-variable
    long movieId = Long.valueOf(request.pathVariable("id"));

    // get Movie data from request object
    Mono<Movie> movieMono = request.bodyToMono(Movie.class);

    // get Movie from repository
    Mono<Movie> responseMono = movieRepository.putMovie(movieId, movieMono);

    // build response
    return responseMono
      .flatMap(cust -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(cust)));
  }

  /**
   *	DELETE a Movie
   */
  public Mono<ServerResponse> deleteMovie(ServerRequest request) {
    // parse id from path-variable
    long movieId = Long.valueOf(request.pathVariable("id"));

    // get Movie from repository
    Mono<String> responseMono = movieRepository.deleteMovie(movieId);

    // build response
    return responseMono
      .flatMap(strMono -> ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(fromObject(strMono)));
  }
}
