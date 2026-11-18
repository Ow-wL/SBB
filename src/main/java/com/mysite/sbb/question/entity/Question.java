package com.mysite.sbb.question.entity;

import com.mysite.sbb.answer.entity.Answer;
import com.mysite.sbb.audit.BaseEntity;
import com.mysite.sbb.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

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

public class Question extends BaseEntity {
    @Id // 기본키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 값을 자동으로 생성
    // IDENTITY -> MySQL, SEQUENCE -> PostgreSQL / Auto -> 자동
    // @Column(name = "question_id")
    // DB 컬럼명 지정 가능
    private Long id; // 아이디 (원칙적으로 Integer, Long 사용)

    @Column(length = 100, nullable = false)
    private String subject; // 질문 제목

    @Column(columnDefinition = "TEXT")
    private String content; // 질문 내용

    @CreatedDate
    private LocalDateTime created; // 생성일

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Answer> answerList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member author;
}
