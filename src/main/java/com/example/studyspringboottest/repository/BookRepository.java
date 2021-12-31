package com.example.studyspringboottest.repository;

import com.example.studyspringboottest.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
