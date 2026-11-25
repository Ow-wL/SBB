package com.mysite.sbb.question.controller;

import com.mysite.sbb.answer.dto.AnswerDto;
import com.mysite.sbb.member.entity.Member;
import com.mysite.sbb.member.service.MemberService;
import com.mysite.sbb.question.dto.QuestionDto;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String list(Model model,
                       @RequestParam(value = "page", defaultValue="0") int page) {
        Page<Question> paging = questionService.getList(page);
        log.info("==========> paging : {}", paging);
        model.addAttribute("paging", paging);
        return "question/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id,
                         AnswerDto answerDto,
                         Model model) {
        log.info("===========> id : {}", id);
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question/detail";
    }

    @PreAuthorize("isAuthenticated()") // 로그인 한 사용자만 접근 가능
    @GetMapping("/create")
    public String createInputForm(QuestionDto questionDto) {
        return "question/inputform";
    }

//    DTO 없이 구현 -> RequestParam 활용
//    @PostMapping("/create")
//    public String createQuestion(@RequestParam("subject") String subject,
//                                 @RequestParam("content") String content) {
//        log.info("===========> subject : {} content : {}", subject, content);
//        questionService.createQuestion(subject, content);
//        return "redirect:/question/list";
//    }

    // Pricipal 추가 - java.security
    @PreAuthorize("isAuthenticated()") // 로그인 한 사용자만 접근 가능
    @PostMapping("/create")
    public String createQuestion(@Valid QuestionDto questionDto,
                                 BindingResult bindingResult,
                                 Principal principal) { // 현재 로그인한 계정 정보 가져오기
        log.info("===========> create - questionDto : {}", questionDto);
        if(bindingResult.hasErrors()) {
            return "question/inputform";
        }

        Member member = memberService.getMember(principal.getName());

        questionService.createQuestion(questionDto, member);
        return "redirect:/question/list";
    }

    //href -> Get 방식
    // 질문 수정을 위해 입력 폼으로 이동
    @PreAuthorize("isAuthenticated()") // 로그인한 사용자만 접근 가능
    @GetMapping("/modify/{id}")
    public String modifyQuestion(@PathVariable("id") Long id,
                                 QuestionDto questionDto,
                                 Principal principal) {

        Question question = questionService.getQuestion(id);
        if(!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }
        questionDto.setSubject(question.getSubject());
        questionDto.setContent(question.getContent());
        return "question/inputForm";
    }

    // 질문 수정하기
    @PreAuthorize("isAuthenticated()") // 로그인한 사용자만 접근 가능
    @PostMapping("/modify/{id}")
    public String modifyQuestion(@PathVariable("id") Long id,
                                 @Valid QuestionDto questionDto,
                                 BindingResult bindingResult,
                                 Principal principal) {
        log.info("===========> modify - questionDto : {}", questionDto);

        if (bindingResult.hasErrors()) {
            return "question/inputForm";
        }

        Question question = questionService.getQuestion(id);
        if(!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
        }

        questionService.modifyQuestion(question, questionDto);

        return "redirect:/question/" + id;
    }
}
