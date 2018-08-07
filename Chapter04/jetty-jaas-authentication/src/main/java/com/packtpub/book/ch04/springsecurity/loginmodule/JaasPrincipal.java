package com.packtpub.book.ch04.springsecurity.loginmodule;

import java.io.Serializable;
import java.security.Principal;

/**
 * Create a custom Principal object which can be used for your application
 * Any logic can be used to build the principal object
 */
public class JaasPrincipal implements Principal, Serializable {

  private static final long serialVersionUID = 1L;
private String username;

  public JaasPrincipal(String username) {
    this.username = username;
  }

  @Override
  public String getName() {
    return "Authenticated_"+this.username;
  }
}
