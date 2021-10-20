/*
HelloController의 단위 테스트
 */
package com.jojoIdu.book.springboot;
import com.jojoIdu.book.springboot.web.HelloController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//SpringBoot Test와 Junit사이에 연결자 역할
@ExtendWith(SpringExtension.class)
//Spring MVC
//선언할 경우, @Controller, @ControllerAdvice 등을 사용할 수 있음.
//하지만 @Service, @Component, @Repository는 사용할 수 없음!
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
    @Autowired
    //스프링 MVC 테스트의 시작점. 이 클래스를 통해, HTTP GET, POST등에 대한 API테스트를 할 수 있다.
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";
        //MockMvc를 통해 /hello 주소로 HTTP GET요청
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }
}
