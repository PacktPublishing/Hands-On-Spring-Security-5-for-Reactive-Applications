package com.packtpub.book.ch06.springsecurity.model;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthResponse {
  private String token;
  private String username;
}
