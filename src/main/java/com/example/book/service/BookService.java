package com.example.book.service;


import com.example.book.dao.Book;
import com.example.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// 특정 동작을 수행하기 위해서 리포지토리ㅔㅇ서 값을ㅇ 실어다 나릅니다.
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    // 모든 책 조회
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }






}
