package com.packtpub.book.ch06.springsecurity.model;

import lombok.*;

import java.io.Serializable;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthRequest implements Serializable {
  private static final long serialVersionUID = 1L;
  private String username;
  private String password;
}
