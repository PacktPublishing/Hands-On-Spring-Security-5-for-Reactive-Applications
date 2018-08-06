package com.packtpub.book.ch06.springsecurity.controller;

import com.packtpub.book.ch06.springsecurity.model.Movie;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class MovieController {

    private static Movie[] movies = new Movie[4];

    @PostConstruct
    public void initIt() {
        movies[0] = new Movie(Long.valueOf(1), "Moonlight", "Drama");
        movies[1] = new Movie(Long.valueOf(2), "Dunkirk", "Drama/Thriller");
        movies[2] = new Movie(Long.valueOf(3), "Get Out", "Mystery/Thriller");
        movies[3] = new Movie(Long.valueOf(4), "The Shape of Water", "Drama/Thriller");
    }

    @RequestMapping(value = "/movie1", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("#oauth2.hasScope('movie') and #oauth2.hasScope('read')")
    public String[] getMovie() {
        String[] movies = new String[]{"Movie 1", "Movie 2", "Movie 3"};

        return movies;
    }

    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("#oauth2.hasScope('movie') and #oauth2.hasScope('read')")
    public Movie[] getMovies() {
        initIt();
        return movies;
    }
}
