package com.example.studyspringboottest;

import com.example.studyspringboottest.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookJSONTest {

    @Autowired
    private JacksonTester<Book> json;

    @Test
    public void json_테스트() throws Exception {
        Book book = Book.builder()
                    .title("테스트")
                    .build();
        String content = "{\"title\": \"테스트\"}";

        assertThat(json.parseObject(content).getTitle()).isEqualTo(book.getTitle());
        assertThat(json.parseObject(content).getPublishedAt()).isNull();
        assertThat(json.write(book)).isEqualToJson("/test.json");
        assertThat(json.write(book)).hasJsonPathStringValue("title");
        assertThat(json.write(book)).extractingJsonPathStringValue("title").isEqualTo("테스트");
    }
}
