package com.packtpub.book.ch06.springsecurity.auth;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JWTUtil {
  public final static  String DEFAULT_SECRET = "packtpubpacktpubpacktpubpacktpub";
  private static final String BEARER = "Bearer ";

  public static String generateToken(String subjectName, Collection<? extends GrantedAuthority> authorities) {
    JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
      .subject(subjectName)
      .issuer("javacodebook.com")
      .expirationTime(new Date(new Date().getTime() + 30 * 1000))
      .claim("auths", authorities.parallelStream().map(auth -> (GrantedAuthority) auth).map(a -> a.getAuthority()).collect(Collectors.joining(",")))
      .build();

    SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

    try {
      signedJWT.sign(JWTUtil.getJWTSigner());
    } catch (JOSEException e) {
      e.printStackTrace();
    }

    return signedJWT.serialize();
  }

  public static JWSSigner getJWTSigner() {
    JWSSigner jwsSigner;
    try {
      jwsSigner = new MACSigner(DEFAULT_SECRET) ;
    } catch (KeyLengthException e) {
      jwsSigner = null;
    }
    return jwsSigner;
  }

  public static String getAuthorizationPayload(ServerWebExchange serverWebExchange) {
    return serverWebExchange.getRequest()
      .getHeaders()
      .getFirst(HttpHeaders.AUTHORIZATION);
  }

  public static Predicate<String> matchBearerLength(){
    Predicate<String> matchBearerLength = authValue -> authValue.length() > BEARER.length();
    return matchBearerLength;
  }

  public static Function<String,String> getBearerValue(){
    Function<String,String> getBearerValue = authValue -> authValue.substring(BEARER.length(), authValue.length());
    return getBearerValue;
  }

  public static Mono<SignedJWT> verifySignedJWT(String token) {
    try {
      return Mono.just(SignedJWT.parse(token));
    } catch (ParseException e) {
      return Mono.empty();
    }
  }

  public static Authentication getUsernamePasswordAuthenticationToken(Mono<SignedJWT> signedJWTMono) {
    SignedJWT signedJWT = signedJWTMono.block();
    String subject;
    String auths;

    try {
      subject = signedJWT.getJWTClaimsSet().getSubject();
      auths = (String) signedJWT.getJWTClaimsSet().getClaim("auths");
    } catch (ParseException e) {
      return null;
    }
    List authorities = Stream.of(auths.split(","))
      .map(a -> new SimpleGrantedAuthority(a))
      .collect(Collectors.toList());

    return new UsernamePasswordAuthenticationToken(subject, null, authorities);
  }
}
