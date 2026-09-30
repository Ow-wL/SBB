package com.mysite.sbb.answer.repository;

import com.mysite.sbb.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    // 자동으로 test 만들기 -> Alt + Insert or 우클릭 후 Generate
}
