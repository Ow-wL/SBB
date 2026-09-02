package com.mysite.sbb.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    // 주소창에서 /를 입력해서 오면 아래 index 메소드가 자동 실행됨.
    // 실행 후 resourses/templates로 이동

    @GetMapping("/")
    public String index() {
        return "index";
    }
}

