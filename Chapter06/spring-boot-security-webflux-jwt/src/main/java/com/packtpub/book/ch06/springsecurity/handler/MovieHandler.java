package com.packtpub.book.ch06.springsecurity.handler;

import com.packtpub.book.ch06.springsecurity.model.Movie;
import com.packtpub.book.ch06.springsecurity.repository.MovieRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class MovieHandler {

  private final MovieRepository movieRepository;

  public MovieHandler(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
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
      .flatMap(movie -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(movie)))
      .switchIfEmpty(notFound);
  }

  /**
   * POST a Movie
   */
  public Mono<ServerResponse> saveMovie(ServerRequest request) {
    Mono<Movie> movieMono = request.bodyToMono(Movie.class);
    movieRepository.saveMovie(movieMono);

    return listMovies(request);
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
