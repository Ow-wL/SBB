package com.mysite.sbb.question.repository;

import com.mysite.sbb.question.entity.Question;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;
    // Dependency Injection

    @Test
    void test() {
        System.out.println("테스트 확인중...");
    }

    // @Test
    // @Transactional
    // -> 테스트 때 실행 후 삭제하고 나옴 or application.yml -> ddl-auto: update를 create로 수정
    void testSave() {
        Question q1 = new Question();
        q1.setSubject("SBB가 뭔가요?");
        q1.setContent("SBB에 대해서 알고 싶습니다.");
        q1.setCreated(LocalDateTime.now());
        Question saved = questionRepository.save(q1);
        assertEquals(1, saved.getId());

        Question q2 = Question.builder()
                .subject("스프링 부트도 어려운가요?")
                .content("스프링은 왜 어렵다고 하는거죠?")
                .created(LocalDateTime.now())
                .build();
        Question saved2 = questionRepository.save(q2);
        assertEquals(2, saved2.getId());
    }

    @Test
    void testFindAll() {
        List<Question> questionList = questionRepository.findAll();
        assertEquals(3, questionList.size());
        Question question = questionList.get(0);
        assertEquals("SBB가 뭔가요?", question.getSubject());
    }

    @Test
    void testFindById() {
        Optional<Question> byId = questionRepository.findById(1L);
        // Optional -> 있을 지 없을 지 모르기에, Null 타입 대비용 Optional로 사용. Question 아님!
        if (byId.isPresent()) {
            Question question = byId.get();
            assertEquals(1, question.getId());
        }

        Question question = questionRepository.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException("해당 객체가 없습니다."));
        assertEquals(1, question.getId());
    }

    @Test
    void testFindBySubject() {
        Question question = questionRepository.findBySubject("SBB가 뭔가요?");
        assertNotNull(question);
        assertEquals(1, question.getId());
    }

    @Test
    void testFindBySubjectAndContent() {
        Question question = questionRepository.findBySubjectAndContent("SBB가 뭔가요?", "SBB에 대해서 알고 싶습니다.")
                .orElseThrow(() -> new EntityNotFoundException("해당 자료가 없습니다."));
        assertEquals(1, question.getId());
    }

    @Test
    void testFindBySubjectLike() {
        String keyWord = "SBB";
        List<Question> list = questionRepository.findBySubjectLike("%" + keyWord + "%");
        assertEquals(1, list.size());
    }

    @Test
    void testSubjectUpdate() {
        Question question = questionRepository.findById(2L)
                .orElseThrow(() -> new EntityNotFoundException("해당 정보가 없습니다."));
        System.out.println("변경 전 : " + question);
        question.setSubject("새로운 질문 ");
        Question saved = questionRepository.save(question);
        System.out.println("변경 후 : " + saved);
    }

    @Test
    void testDelete() {
        questionRepository.deleteById(2L);
        assertEquals(1, questionRepository.count());
    }
}
