package com.mysite.sbb.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    // 주소창에서 /를 입력해서 오면 아래 index 메소드가 자동 실행됨.
    // 실행 후 resources/templates로 이동

    // 매핑 실패 시 404 에러, 매핑 성공 후 목표 페이지가 없으면 500 에러

    @GetMapping("/")
//    @ResponseBody
    // -> html이 아닌, 데이터 값으로 전달 (일부만 데이터로 전달하고 싶을 때 사용, 이 외에는 RestController)
    public String index() {
        return "index";
    }

    @GetMapping("/temp")
    public String temp() {
        return "temp";
    }
}

