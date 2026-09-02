package com.mysite.sbb.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
public class DataController {

    // RestController 인 경우, templates의 html 파일을 찾아가는 것이 아닌 json을 통해 데이터 그대로 출력

    @GetMapping("/data")
    public Point data() {
        Point p = new Point(10, 20);

        return p;
    }
}

