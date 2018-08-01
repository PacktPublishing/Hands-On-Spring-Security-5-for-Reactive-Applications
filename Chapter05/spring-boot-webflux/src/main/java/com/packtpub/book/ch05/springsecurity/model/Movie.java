package com.packtpub.book.ch05.springsecurity.model;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

  private Long id;
  private String title;
  private String genre;

}
