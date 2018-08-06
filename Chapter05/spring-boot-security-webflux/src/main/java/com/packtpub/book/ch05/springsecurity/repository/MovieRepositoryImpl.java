package com.packtpub.book.ch05.springsecurity.repository;

import com.packtpub.book.ch05.springsecurity.model.Movie;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MovieRepositoryImpl implements MovieRepository {
  private Map<Long, Movie> movies = new HashMap<Long, Movie>();

  @PostConstruct
  public void initIt() throws Exception {
    movies.put(Long.valueOf(1), new Movie(Long.valueOf(1), "Moonlight", "Drama"));
    movies.put(Long.valueOf(2), new Movie(Long.valueOf(2), "Dunkirk", "Drama/Thriller"));
    movies.put(Long.valueOf(3), new Movie(Long.valueOf(3), "Get Out", "Mystery/Thriller"));
    movies.put(Long.valueOf(4), new Movie(Long.valueOf(4), "The Shape of Water", "Drama/Thriller"));
  }

  @Override
  public Mono<Movie> getMovieById(Long id) {
    return Mono.just(movies.get(id));
  }

  @Override
  public Flux<Movie> listMovies() {
    return Flux.fromIterable(this.movies.values());
  }

  @Override
  public Mono<Void> saveMovie(Mono<Movie> monoMovie) {
    Mono<Movie> movieMono =  monoMovie.doOnNext(movie -> {
      // do post
      movies.put(movie.getId(), movie);

      // log on console
      System.out.println("Movie Repository POST:" + movie);
    });

    return monoMovie.then();
  }

  @Override
  public Mono<Movie> putMovie(Long id, Mono<Movie> monoMovie) {
    Mono<Movie> movieMono =  monoMovie.doOnNext(movie -> {
      // reset Movie.Id
      movie.setId(id);

      // do put
      movies.put(id, movie);

      // log on console
      System.out.println("Movie Repository PUT:" + movie);
    });

    return monoMovie;
  }

  @Override
  public Mono<String> deleteMovie(Long id) {
    // delete processing
    movies.remove(id);
    return Mono.just("Delete Succesfully!");
  }
}