package com.packtpub.book.ch05.springsecurity.controller;

import com.packtpub.book.ch05.springsecurity.model.Movie;
import com.packtpub.book.ch05.springsecurity.repository.ReactiveMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class MovieController {

    @Autowired
    private ReactiveMovieRepository reactiveMovieRepository;

    @GetMapping("/movies")
    @PreAuthorize("hasRole('ADMIN')")
    public Flux<Movie> getAllMovies() {
        return reactiveMovieRepository.findAll();
    }

    @GetMapping("/movies/{genre}")
    public Flux<Movie> getAllMoviesByGenre(@PathVariable String genre) {
        return reactiveMovieRepository.findByGenre(genre);
    }

    @GetMapping("/movies/{title}/{genre}")
    public Flux<Movie> getAllMoviesByTitleAndGenre(@PathVariable String title, @PathVariable String genre) {
        return reactiveMovieRepository.findByTitleAndGenre(title, genre);
    }

    @PostMapping("/movies")
    public Mono<Movie> createMovies(@Valid @RequestBody Movie movie) {
        return reactiveMovieRepository.save(movie);
    }
}