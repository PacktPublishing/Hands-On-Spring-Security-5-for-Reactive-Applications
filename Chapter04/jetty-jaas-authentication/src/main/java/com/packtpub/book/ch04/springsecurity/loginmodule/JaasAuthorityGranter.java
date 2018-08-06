package com.packtpub.book.ch04.springsecurity.loginmodule;

import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Collections;
import java.util.Set;

/**
 * Custom AuthorityGranter which looks at the username and then grants authorities
 */
@Component
public class JaasAuthorityGranter implements AuthorityGranter {

  @Override
  public Set<String> grant(Principal principal) {
    if (principal.getName().equalsIgnoreCase("Authenticated_admin")) {
      return Collections.singleton("ROLE_ADMIN");
    } else if (principal.getName().equalsIgnoreCase("Authenticated_user")) {
      return Collections.singleton("ROLE_USER");
    }
    return Collections.singleton("ROLE_USER");
  }
}
