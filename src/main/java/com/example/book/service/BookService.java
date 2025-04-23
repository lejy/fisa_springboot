package com.example.book.service;


import com.example.book.dao.Book;
import com.example.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }


    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }


    public List<Book> getBookByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getBookByTitleAndAuthor(String title, String author) {
        return bookRepository.findByTitleContainingAndAuthorContaining(title, author);
    }


    public Book updateBookById2(Long id, Book book) {
        Book existingBook = bookRepository.findById(id).orElse(null); // id가 없으면 null을 반환
        if (book.getTitle() != null){
            existingBook.setTitle(book.getTitle());
        } else if (book.getAuthor() != null){
            existingBook.setAuthor(book.getAuthor());
        } else if (book.getPage() != 0){
            existingBook.setPage(book.getPage());
        }
        // 3. 그 결과를 service를 통해 repository로 전달

        return bookRepository.save(existingBook);
    }
}
