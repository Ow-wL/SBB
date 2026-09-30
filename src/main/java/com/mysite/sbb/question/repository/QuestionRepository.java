package com.mysite.sbb.question.repository;

import com.mysite.sbb.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Question findBySubject(String subject);
    Optional<Question> findBySubjectAndContent(String subject, String content);

    // Like 검색의 리턴타입은 주로 List
    List<Question> findBySubjectLike(String subject);
}
