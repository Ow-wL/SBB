package com.mysite.sbb.book.repository;

import com.mysite.sbb.book.dto.BookDto;
import com.mysite.sbb.book.entity.Book;
import com.mysite.sbb.book.service.BookService;
import com.mysite.sbb.question.dto.QuestionDto;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.repository.QuestionRepository;
import com.mysite.sbb.question.service.QuestionService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    // Dependency Injection

    @Autowired
    private BookService bookService;

    @Test
    void test() {
        System.out.println("테스트 확인중...");
    }

    @Test
    // @Transactional
    // -> 테스트 때 실행 후 삭제하고 나옴 or application.yml -> ddl-auto: update를 create로 수정
    void testSave() {
        Book q1 = new Book();
        q1.setSubject("클린 코드");
        q1.setAuthor("로버트 마틴");
        q1.setContent("읽어봤는데 재미있네요");
        q1.setRating(String.valueOf(1));
        q1.setCreated(LocalDateTime.now());
        Book saved = bookRepository.save(q1);
        //assertEquals(1, saved.getId());

        Book q2 = new Book();
        q2.setSubject("HTTP 완벽 가이드");
        q2.setAuthor("데이빗 고울리");
        q2.setContent("읽어봤는데 재미있네요2");
        q2.setRating(String.valueOf(5));
        q2.setCreated(LocalDateTime.now());
        Book saved2 = bookRepository.save(q2);
        //assertEquals(1, saved.getId());
    }

    @Test
    void testLargeData() {
        for (int i = 1; i < 120; i++) {
            BookDto bookDto = BookDto.builder()
                    .subject("책 테스트 데이터 입니다. [" + i + "]")
                    .content("감상문입니다. [" + i + "]")
                    .author("작가[" + i + "]")
                    .rating("3")
                    .build();
            bookService.createBook(bookDto);
        }
    }
}
