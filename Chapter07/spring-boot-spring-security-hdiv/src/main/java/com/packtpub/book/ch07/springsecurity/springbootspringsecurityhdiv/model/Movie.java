package com.packtpub.book.ch07.springsecurity.springbootspringsecurityhdiv.model;

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
