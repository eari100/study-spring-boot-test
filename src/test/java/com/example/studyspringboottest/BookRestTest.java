package com.example.studyspringboottest;

import com.example.studyspringboottest.domain.Book;
import com.example.studyspringboottest.service.BookRestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpServerErrorException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(BookRestService.class)
public class BookRestTest {

    @Autowired
    private BookRestService bookRestService;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Test
    public void rest_테스트() {
        mockRestServiceServer.expect(requestTo("/rest/test"))
                .andRespond(withSuccess(new ClassPathResource("/test.json", getClass()), MediaType.APPLICATION_JSON));

        final Book book = bookRestService.getRestBook();

        assertThat(book.getTitle()).isEqualTo("테스트");
    }

    @Test
    public void rest_error_테스트() {
        mockRestServiceServer.expect(requestTo("/rest/test"))
                .andRespond(withServerError());
        assertThatThrownBy(() -> bookRestService.getRestBook())
                .isInstanceOf(HttpServerErrorException.class);
    }

}
