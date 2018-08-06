package com.packtpub.book.ch06.springsecurity;

import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OAuthSpringBootAutoConfigTests {
  @Autowired
  private MockMvc mvc;

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void everythingIsSecuredByDefault() throws Exception {
    this.mvc.perform(get("/").accept(MediaTypes.HAL_JSON))
      .andExpect(status().isUnauthorized());
    this.mvc.perform(get("/movies").accept(MediaTypes.HAL_JSON))
      .andExpect(status().isUnauthorized());
    this.mvc.perform(get("/movies/1").accept(MediaTypes.HAL_JSON))
      .andExpect(status().isUnauthorized());
  }

  @Test
  @Ignore
  public void accessingRootUriPossibleWithUserAccount() throws Exception {
    MockHttpServletRequestBuilder request = get("/")
      .accept(MediaTypes.HAL_JSON)
      .with(httpBasic("oAuthClientAppID", "secret"));
    this.mvc.perform(request)
      .andExpect(
        header().string("Content-Type", MediaTypes.HAL_JSON_UTF8_VALUE))
      .andExpect(status().is4xxClientError()).andDo(print());
  }
  @Test
  public void useAppSecretsPlusUserAccountToGetBearerToken() throws Exception {
    MockHttpServletRequestBuilder tokenRequest = post("/oauth/token")
      .with(httpBasic("oAuthClientAppID", "secret"))
      .param("grant_type", "password")
      .param("scope", "read")
      .param("username", "user")
      .param("password", "password");
    MvcResult result = this.mvc.perform(tokenRequest)
      .andExpect(status().isOk())
      .andReturn();
  }
}
