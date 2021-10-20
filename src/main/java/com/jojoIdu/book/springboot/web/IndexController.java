package com.jojoIdu.book.springboot.web;

import com.jojoIdu.book.springboot.service.posts.PostsService;
import com.jojoIdu.book.springboot.web.dto.PostsResponseDto;
import jdk.internal.module.IllegalAccessLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    /*
    @GetMapping("/")
    public String index(){
        //머스테치를 사용하면,, 컨트롤러(뷰 반환) 에서 문자열을 반환할 때
        //앞의 경로와 뒤의 확장자는 자동으로 지정된다
        //src/main/resources/templates/문자열.mustache
        return "index";
    }
    */

    @GetMapping("/")
    //Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있음
    public String index(Model model){
        //postsService.findAllDesc()의 결과를 posts로 뷰에 전달
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
