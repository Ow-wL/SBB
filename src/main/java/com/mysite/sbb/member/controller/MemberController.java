package com.mysite.sbb.member.controller;

import com.mysite.sbb.member.dto.MemberDto;
import com.mysite.sbb.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String login(MemberDto memberDto) {
        return "member/login";
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/signup"; // signup.html 파일을 반환
    }

    @PostMapping("/signup")
    public String signUp(@Valid MemberDto memberDto, BindingResult bindingResult, Model model) {
        log.info("=====> memberDto: {}", memberDto);

        if (bindingResult.hasErrors()) {
            return "member/signup";
        }

        if (!memberDto.getPassword1().equals(memberDto.getPassword2())) {
            // 필드명, 오류 코드, 오류 메시지
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
            return "member/signup"; // 스크립트 처리
        }

        try {
            memberService.createMember(memberDto);
        } catch(DataIntegrityViolationException e){
            log.info("=============================회원가입 실패: 이미 등록된 사용자입니다.");
            model.addAttribute("errorMsg","이미 등록된 사용자입니다.");
            return "member/signup";
        } catch(Exception e){
            log.info("=============================회원가입 실패: " + e.getMessage());
            model.addAttribute("errorMsg", e.getMessage());
            return "member/signup";
        }

        return "redirect:/";
    }
}
