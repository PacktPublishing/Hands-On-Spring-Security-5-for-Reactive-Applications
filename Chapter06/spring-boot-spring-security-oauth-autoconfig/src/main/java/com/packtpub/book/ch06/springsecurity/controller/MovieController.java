package com.packtpub.book.ch06.springsecurity.controller;

public class MovieController {

//  @Autowired
//  public MovieRepositoryH2 movieRepositoryH2;
//
//  @RequestMapping(value = "/movie", method = RequestMethod.GET)
//  @ResponseBody
//  @PreAuthorize("#oauth2.hasScope('movie') and #oauth2.hasScope('read')")
//  public Collection<Movie> getMovies() {
//    Iterable<Movie> iterable = movieRepositoryH2.findAll();
//
//    Collection<Movie> movies = iterableToCollection(iterable);
//    return movies;
//  }
//
//  public static <T> Collection<T> iterableToCollection(Iterable<T> iterable)
//  {
//    Collection<T> collection = new ArrayList<>();
//    iterable.forEach(collection::add);
//
//    return collection;
//  }
}
