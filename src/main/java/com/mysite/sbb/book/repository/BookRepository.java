package com.mysite.sbb.book.repository;

import com.mysite.sbb.book.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findBySubject(String subject);
    Optional<Book> findBySubjectAndContent(String subject, String content);

    // Like 검색의 리턴타입은 주로 List
    List<Book> findBySubjectLike(String subject);

    // 페이징
    Page<Book> findAll(Pageable pageable);
}
