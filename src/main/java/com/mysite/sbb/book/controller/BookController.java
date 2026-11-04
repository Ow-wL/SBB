package com.mysite.sbb.book.controller;

import com.mysite.sbb.answer.dto.AnswerDto;
import com.mysite.sbb.book.dto.BookDto;
import com.mysite.sbb.book.entity.Book;
import com.mysite.sbb.book.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService BookService;

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue="0") int page) {
        Page<Book> paging = BookService.getList(page);
        log.info("==========> paging : {}", paging);
        model.addAttribute("paging", paging);
        return "book/list";
    }

    /*
    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id,
                         AnswerDto answerDto,
                         Model model) {
        log.info("===========> id : {}", id);
        Book question = BookService.getQuestion(id);
        model.addAttribute("question", question);
        return "question/detail";
    }
    */

    @GetMapping("/create")
    public String createInputForm(BookDto bookDto) {
        return "book/inputform";
    }

    @PostMapping("/create")
    public String createBook(@Valid BookDto bookDto,
                                 BindingResult bindingResult) {
        log.info("===========> bookDto : {}", bookDto);
        if(bindingResult.hasErrors()) {
            return "book/inputform";
        }

        BookService.createBook(bookDto);
        return "redirect:/book/list";
    }
}
