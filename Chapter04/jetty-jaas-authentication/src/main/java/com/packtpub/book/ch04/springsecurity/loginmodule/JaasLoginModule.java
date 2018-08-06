package com.packtpub.book.ch04.springsecurity.loginmodule;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Map;

public class JaasLoginModule implements LoginModule {
  private Subject subject;
  private String username;
  private String password;

  // Gather information and then use this in the login method
  @Override
  public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
    this.subject = subject;

    NameCallback nameCallback = new NameCallback("Username:");
    PasswordCallback passwordCallback = new PasswordCallback("Password:", false);
    try {
      callbackHandler.handle(new Callback[] { nameCallback, passwordCallback });
    } catch (IOException e) {
      e.printStackTrace();
    } catch (UnsupportedCallbackException e) {
      e.printStackTrace();
    }
    username = nameCallback.getName();
    password = new String(passwordCallback.getPassword());
  }

  // This is where actual login happens. Can implement any logic as required by your application
  // In our sample we are just doing a hard-coded comparison of username and password
  @Override
  public boolean login() throws LoginException {
    if (username == null || (username.equalsIgnoreCase("")) ||
      password == null || (password.equalsIgnoreCase(""))) {
      throw new LoginException("Username and password is mandatory.");
    } else if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("password")) {
        subject.getPrincipals().add(new JaasPrincipal(username));
        return true;
    } else if (username.equalsIgnoreCase("user") && password.equalsIgnoreCase("password")) {
        subject.getPrincipals().add(new JaasPrincipal(username));
        return true;
    }
    return false;
  }

  //Other overridden methods
  @Override
  public boolean commit() throws LoginException {
    return true;
  }

  @Override
  public boolean abort() throws LoginException {
    return false;
  }

  @Override
  public boolean logout() throws LoginException {
    return false;
  }
}
