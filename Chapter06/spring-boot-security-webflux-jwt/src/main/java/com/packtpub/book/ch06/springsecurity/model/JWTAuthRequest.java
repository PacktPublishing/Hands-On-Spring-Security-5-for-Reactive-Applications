package com.packtpub.book.ch06.springsecurity.model;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthRequest implements Serializable {
  private String username;
  private String password;
}
