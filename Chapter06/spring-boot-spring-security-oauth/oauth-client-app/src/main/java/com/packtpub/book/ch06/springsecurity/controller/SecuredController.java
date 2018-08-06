package com.packtpub.book.ch06.springsecurity.controller;

import com.packtpub.book.ch06.springsecurity.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SecuredController {

    @Value("${movie.base-uri}")
    private String movieApiBaseUri;

    @Autowired
    @Qualifier("movieAppRestTemplate")
    private OAuth2RestTemplate movieAppRestTemplate;

    @RequestMapping(value = "/movie", method = RequestMethod.GET)
    public String root() {
        return "redirect:/movie/index";
    }

    @RequestMapping(value = "/movie/index", method = RequestMethod.GET)
    @ResponseBody
    public Movie[] index() {
        Movie[] movies = movieAppRestTemplate.getForObject(movieApiBaseUri, Movie[].class);

        return movies;
    }
}
