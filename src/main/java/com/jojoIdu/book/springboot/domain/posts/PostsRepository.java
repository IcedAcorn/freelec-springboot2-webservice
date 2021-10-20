/*
DB Layer 라고 보면 됨
JpaRepository<Entity, PK타입>을 상속하면 기본적인 CRUD메서드 자동으로 생성
 */
package com.jojoIdu.book.springboot.domain.posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
