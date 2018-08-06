package com.packtpub.book.ch06.springsecurity.repository;

import com.packtpub.book.ch06.springsecurity.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;

public interface MovieRepositoryH2 extends CrudRepository<Movie, Long> {

  @Override
  @PreAuthorize("#oauth2.hasScope('read')")
  Iterable<Movie> findAll();

  @Override
  @PreAuthorize("#oauth2.hasScope('read')")
  Optional<Movie> findById(Long aLong);

  @Override
  @PreAuthorize("#oauth2.hasScope('write')")
  <S extends Movie> S save(S entity);

}
