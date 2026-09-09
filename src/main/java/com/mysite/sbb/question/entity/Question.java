package com.mysite.sbb.question.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString(exclude = "")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    @Id // 기본키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 값을 자동으로 생성
    // IDENTITY -> MySQL, SEQUENCE -> PostgreSQL / Auto -> 자동
    private Long id; // 아이디 (원칙적으로 Integer, Long 사용)

    @Column(length = 100, nullable = false)
    private String subject; // 질문 제목

    @Column(columnDefinition = "TEXT")
    private String content; // 질문 내용

    private LocalDateTime created; // 생성일
}
