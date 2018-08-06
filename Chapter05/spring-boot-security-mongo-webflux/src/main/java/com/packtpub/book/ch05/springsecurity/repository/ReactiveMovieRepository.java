package com.packtpub.book.ch05.springsecurity.repository;

import com.packtpub.book.ch05.springsecurity.model.Movie;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ReactiveMovieRepository extends ReactiveMongoRepository<Movie, Long> {

    @Query("{ 'title': ?0, 'genre': ?1}")
    Flux<Movie> findByTitleAndGenre(String title, String genre);

    @Query("{ 'genre': ?0}")
    Flux<Movie> findByGenre(String genre);
}
