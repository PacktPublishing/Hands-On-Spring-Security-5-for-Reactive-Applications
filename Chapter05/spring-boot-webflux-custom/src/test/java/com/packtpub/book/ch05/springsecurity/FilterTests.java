package com.packtpub.book.ch05.springsecurity;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FilterTests {

    @Autowired
    private WebTestClient webTestClient;

    /**
     * filtertest1 path is annotation-based
     * filtertest2 path is functional-based
     *
     * WebFilter applies to both annotation-based and functional-based
     * HandlerFilterFunction applies to functional-based
     */

    /**
     * When path is "filtertest1" with path variable "value1" WebFilter is applied.
     * Checks being done:
     * response having value "value1"
     * You should see additional response header namely "filter-added-header".
     */
    @Test
    public void filtertest1_with_pathVariable_equalTo_value1_apply_WebFilter() {
        EntityExchangeResult<String> result = webTestClient.get().uri("/filtertest1/value1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult();

        Assert.assertEquals(result.getResponseBody(), "value1");
        Assert.assertEquals(result.getResponseHeaders().getFirst("filter-added-header"), "filter-added-header-value");
    }

    /**
     * When path is "filtertest1" with path variable "value2" HandlerFilterFunction is applied.
     * Since HandlerFilterFunction applies to only functional-based and not annotation-based, Bad Request response is not set as status.
     */
    @Test
    public void filtertest1_with_pathVariable_equalTo_value2_apply_HandlerFilterFunction() {
        webTestClient.get().uri("/filtertest1/value2")
                .exchange()
                .expectStatus().isOk();
    }

    /**
     * When path is "filtertest2" with path variable "value1" WebFilter is applied.
     * Checks being done:
     * response having value "value1"
     * You should see additional response header namely "filter-added-header".
     */
    @Test
    public void filtertest2_with_pathVariable_equalTo_value1_apply_WebFilter() {
        EntityExchangeResult<String> result = webTestClient.get().uri("/filtertest2/value1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .returnResult();

        Assert.assertEquals(result.getResponseBody(), "value1");
        Assert.assertEquals(result.getResponseHeaders().getFirst("filter-added-header"), "filter-added-header-value");
    }

    /**
     * When path is "filtertest2" with path variable "value2" HandlerFilterFunction is applied.
     * Since HandlerFilterFunction applies to functional-based, Bad Request response is set as status.
     */
    @Test
    public void filtertest2_with_pathVariable_equalTo_value2_apply_HandlerFilterFunction() {
        webTestClient.get().uri("/filtertest2/value2")
                .exchange()
                .expectStatus().isBadRequest();
    }
}
