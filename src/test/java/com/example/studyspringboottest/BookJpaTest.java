package com.example.studyspringboottest;

import com.example.studyspringboottest.domain.Book;
import com.example.studyspringboottest.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookJpaTest {
    private final static String BOOT_TEST_TITLE = "Spring Boot Test Book";

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void Book_저장하기_테스트() {
        Book book = Book.builder()
                    .title(BOOT_TEST_TITLE)
                    .publishedAt(LocalDateTime.now())
                    .build();

        testEntityManager.persist(book);
        assertThat(bookRepository.getById(book.getIdx())).isEqualTo(book);
    }

    @Test
    public void BookList_저장하기_검색_테스트() {
        Book book1 = Book.builder()
                    .title(BOOT_TEST_TITLE+"1")
                    .publishedAt(LocalDateTime.now())
                    .build();
        testEntityManager.persist(book1);

        Book book2 = Book.builder()
                .title(BOOT_TEST_TITLE+"2")
                .publishedAt(LocalDateTime.now())
                .build();
        testEntityManager.persist(book2);

        Book book3 = Book.builder()
                .title(BOOT_TEST_TITLE+"3")
                .publishedAt(LocalDateTime.now())
                .build();
        testEntityManager.persist(book3);

        List<Book> bookList = bookRepository.findAll();
        assertThat(bookList.size()).isEqualTo(3);
        assertThat(bookList).contains(book1, book2, book3);
    }

    @Test
    public void BookList_저장하고_삭제_테스트() {
        Book book1 = Book.builder()
                    .title(BOOT_TEST_TITLE+"1")
                    .publishedAt(LocalDateTime.now())
                    .build();
        testEntityManager.persist(book1);

        Book book2 = Book.builder()
                .title(BOOT_TEST_TITLE+"2")
                .publishedAt(LocalDateTime.now())
                .build();
        testEntityManager.persist(book2);

        bookRepository.deleteAll();

        assertThat(bookRepository.findAll()).isEmpty();
    }
}
