package com.example.book.repository;

import com.example.book.dao.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository                                             // 엔터티명, 엔터티ID 자료명
public interface BookRepository extends JpaRepository<Book, Long> {


    List<Book> findByAuthor(String author);

    List<Book> findByTitleContainingAndAuthorContaining(String title, String author);

    List<Book> findByTitleContaining(String title);

    List<Book> findByPageBetween(int hpage, int lpage);

    @Query("SELECT b FROM Book b WHERE b.title LIKE %:title% OR "
            + "b.author LIKE %:author%")
    List<Book> findByTitleContainingOrAuthorContaining2(@Param("title") String title, @Param("author") String author);

}
