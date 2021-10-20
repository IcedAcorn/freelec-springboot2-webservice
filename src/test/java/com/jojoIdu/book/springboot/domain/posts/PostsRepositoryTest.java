package com.jojoIdu.book.springboot.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
//요 애는 H2 DB를 자동으로 실행해줌
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    //단위테스트가 끝날때 마다 수행
    //단위테스트들이 여러개가 실행될때 h2에 데이터가 그대로 남아있어 다른 테스트에 영향을 줄 수 있어서
    //보통은 테스트간 데이터 침범을 막기위해..
    @AfterEach
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글_저장(){
        String title = "테스트 게시글";
        String content = "테스트 본문";

        //테이블 post에 insert/update 쿼리 날림
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jojoIdu@gmail.com")
                .build());
        
        //post모든 데이터 조회
        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        System.out.println(">>>>>>>>> createdDate:" + posts.getCreatedDate() + ">>>>>>> modifiedDate:" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
