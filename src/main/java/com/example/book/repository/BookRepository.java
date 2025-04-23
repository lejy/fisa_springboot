package com.example.book.repository;

import com.example.book.dao.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository                                             // 엔터티명, 엔터티ID 자료명
public interface BookRepository extends JpaRepository<Book, Long> {


    List<Book> findByAuthor(String author);

    List<Book> findByTitleContainingAndAuthorContaining(String title, String author);
}
