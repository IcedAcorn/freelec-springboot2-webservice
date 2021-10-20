package com.jojoIdu.book.springboot.service.posts;

import com.jojoIdu.book.springboot.domain.posts.Posts;
import com.jojoIdu.book.springboot.domain.posts.PostsRepository;
import com.jojoIdu.book.springboot.web.dto.PostsResponseDto;
import com.jojoIdu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoIdu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당게시글이 없습니다. id:" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id:" + id));

        return new PostsResponseDto(entity);
    }

    //@Transactinal(readOnly = true) 를 주면 트랜잭션 범위는 유지하되 조회 기능만 남겨두어 
    //조회 속도가 개선되기 때문에 등록, 수정,삭제기능이 전혀없는 서비스에서도 사용할 수 있음
    //@Transactional(readOnly = true)
    public List<PostsResponseDto> findAllDesc(){
        //결과로 넘어온 Posts의 Stream을 map을 통해 
        //PostsListResponseDto 변환 -> List로 반환
        return postsRepository.findAllDesc().stream()
                .map(PostsResponseDto::new)     //.map(posts => new PostsResponseDto(posts))와 같음
                .collect(Collectors.toList());
    }
}
