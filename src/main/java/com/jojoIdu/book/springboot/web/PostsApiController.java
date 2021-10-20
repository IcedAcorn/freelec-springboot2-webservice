package com.jojoIdu.book.springboot.web;

import com.jojoIdu.book.springboot.service.posts.PostsService;
import com.jojoIdu.book.springboot.web.dto.PostsResponseDto;
import com.jojoIdu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoIdu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
//Spring MVC의 @RestController은 @Controller와 @ResponseBody의 조합
//차이점 : @Controller의 역할은 Model 객체를 만들어 데이터를 담고 View를 찾는 것이지만,
//@RestController는 단순히 객체만을 반환하고 객체 데이터는 JSON 또는 XML 형식으로 HTTP 응답에 담아서 전송
@RestController
public class PostsApiController {
    //AutoWired를 쓰지않고, @RequiredArgsConstructor를 이용해서
    //생성자로 주입받는 방식(final이 선언된 모든 필드들을 인자값으로 하는 생성자를 롬복에서 생성)
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }
}
