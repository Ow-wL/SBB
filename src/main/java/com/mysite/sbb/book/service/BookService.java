package com.mysite.sbb.book.service;

import com.mysite.sbb.book.dto.BookDto;
import com.mysite.sbb.book.entity.Book;
import com.mysite.sbb.book.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Page<Book> getList(int page) {
//        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "created")
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("created")));
        Page<Book> paging = bookRepository.findAll(pageable);
        return paging;
    }

    public Book getBook(Long id) {
        Book question = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("해당 엔티티 없음!"));
        return question;
    }

    public void createBook(BookDto bookDto) {
        Book book = Book.builder()
                .subject(bookDto.getSubject())
                .content(bookDto.getContent())
                .author(bookDto.getAuthor())
                .rating(String.valueOf(bookDto.getRating()))
                .created(LocalDateTime.now())
                .build();
        bookRepository.save(book);
    }
}
