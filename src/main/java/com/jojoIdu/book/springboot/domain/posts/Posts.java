/*
테이블과 링크될 Entity 클래스
Entity 클래스에선 Setter를 따로 만들지 않는다.
생성자를 통해 최종값을 채운 후 DB에 삽입하는 구조
 */

package com.jojoIdu.book.springboot.domain.posts;

import com.jojoIdu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
//파라미터가 없는 생성자를 자동 생성 public Posts(){}
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity { //BaseTimeEntity 상속
    //Pk
    @Id
    //PK의 생성규칙
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    //빌더 클래스
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
