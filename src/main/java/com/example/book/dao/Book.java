package com.example.book.dao;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity // 테이블 생성, 각 필드들의 하나의 book 객체로 묵는 역할
//@Table(name="books") //테이블명을 db와 다르게 매핑해야 할 때
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long price; // 책 판매금액

    @Column(nullable = false)
    private String title; // 제목
    private String author;
    private String genre;
    private int page;


}
