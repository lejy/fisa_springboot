package com.example.book.controller;

import com.example.book.dao.Book;
import com.example.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("/books")
@Tag(name = "swagger 테스트 API", description = "swagger 테스트를 진행하는 API")
public class BookController {

    @Autowired
    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @Operation(summary = "Book 정보 모두 조회", description = "Book의 전체 정보를 조회합니다.")
    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Operation(summary = "Book 정보 ID로 조회", description = "Book의 id로 조회합니다.")
    @GetMapping("/{id}")   // 옵셔널<자료형>은 null로 발생하는 예외를 처리해주는 wrapper 클래스입니다.
    public Optional<Book> getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @Operation(summary = "Book 정보 저장", description = "Book의 전체 정보를 저장합니다.")
    @PostMapping        //리퀘스트바디 body에 실려오는 값을 북 자료형을 받겠음.
    public Book saveBook(@RequestBody Book book){
        return bookService.saveBook(book);
    }

    @Operation(summary = "id로 삭제")
    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id){
        bookService.deleteBookById(id);
    }

    @Operation(summary = "작가로 책 찾기")
    @GetMapping("/author")
    public List<Book> getBookByAuthor(@RequestParam String author){
        return bookService.getBookByAuthor(author);
    }

    // 책을 저자로 조회하는 API
    @Operation(summary = "제목과 작가로 책 찾기")
    @GetMapping("/select10") // select10?title=스프링부트&author=장정우
    public List<Book> getBookByTitleAndAuthor(@RequestParam String title, @RequestParam String author) {
        return bookService.getBookByTitleAndAuthor(title, author);
    }

    @Operation(summary = "책 수정하기")
    @PutMapping("/{id}") // 전부를 가져가서 변경
    public void updateBookById(@PathVariable Long id, @RequestBody Book book){
        // 1, 전체내용을 books 테이블에서 조회
        book.setId(id);
        // 2. 클라이언트가 body에 준 book의 모든 변경사항을 행에 반영한다.
        // 3. 그 결과를 service를 통해 repository로 전달한다.
        bookService.saveBook(book);

    }

    @PatchMapping("/{id}") // 변경할 사항만 가져가서 변경
    public void updateBookById2(@PathVariable Long id, @RequestBody Book book) {
        // 1. 전체 내용을 books 테이블에서 조회
        // 2. 클라이언트가 body에 준 book의 모든 일부 변경사항을 행에 반영한다.
        // 3. 그 결과를 service를 통해 repository로 전달한다.
        bookService.updateBookById2(id,book);
    }
}
