/*
Application.class에서
@EnableJpaAuditing를 제거하고 분리
 */
package com.jojoIdu.book.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration  //@WebMvcTest는 일반적인 @Configration은 스캔하지 않는다
@EnableJpaAuditing //JPA 활성화
public class JpaConfig {
}
