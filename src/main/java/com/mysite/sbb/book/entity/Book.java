package com.mysite.sbb.book.entity;

import com.mysite.sbb.answer.entity.Answer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "answerList")
@NoArgsConstructor
@AllArgsConstructor
@Builder

// @Table(name = "ABCD")
// 테이블명 지정 가능 but, 기본 클래스 명이니 통일할 것

public class Book {
    @Id // 기본키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 값을 자동으로 생성
    // IDENTITY -> MySQL, SEQUENCE -> PostgreSQL / Auto -> 자동
    // @Column(name = "question_id")
    // DB 컬럼명 지정 가능
    private Long id; // 아이디 (원칙적으로 Integer, Long 사용)

    @Column(length = 100, nullable = false)
    private String subject; // 도서 제목

    @Column(columnDefinition = "TEXT")
    private String author; // 저자

    @Column(columnDefinition = "Integer")
    private String rating; // 평점

    @Column(columnDefinition = "TEXT")
    private String content; // 감상평

    private LocalDateTime created; // 작성일시
}
