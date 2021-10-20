package com.jojoIdu.book.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){
        //머스테치를 사용하면,, 컨트롤러(뷰 반환) 에서 문자열을 반환할 때
        //앞의 경로와 뒤의 확장자는 자동으로 지정된다
        //src/main/resources/templates/문자열.mustache
        return "index";
    }
}
