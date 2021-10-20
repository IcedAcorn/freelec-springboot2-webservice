/*
이 프로젝트의 메인 클래스입니다.
 */

package com.jojoIdu.book.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//Jpa Auditing 활성화
@EnableJpaAuditing
//스프링 부트의 자동설정 어노테이션
//@SpringBootApplication 위치부터 설정을 읽어가기 때문에 메인클래스는 항상 최상단에 위치해야 함
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        //내장 WAS를 실행 (톰캣 필요x)
        SpringApplication.run(Application.class, args);
    }
}
