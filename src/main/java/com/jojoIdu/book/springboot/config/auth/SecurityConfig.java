package com.jojoIdu.book.springboot.config.auth;

import com.jojoIdu.book.springboot.domain.user.Role;
import jdk.jfr.Enabled;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
//Spring Security설정을 활성화
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CutsomOAuth2UserService cutsomOAuth2UserService;

    protected void configure(HttpSecurity http) throws Exception{
        //h2-console화면을 사용하기 위해 해당 옵션들은 disable
        http.csrf().disable().headers().frameOptions().disable()
                .and()
                //URL별 권한을 관리를 설정하는 옵션의 시작점
                .authorizeRequests()
                //permitAll 전체 열람 권한
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                //Role : User에게만 권한
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                //설정값 이외의 나머지 URL들에대해, authenticated를 추가하여 모두 인증된 사용자들에게만 허용되도록 함
                .anyRequest().authenticated()
                .and()
                //로그아웃 성공시 "/"로 리다이렉트
                .logout().logoutSuccessUrl("/")
                .and()
                //oauth2로그인 성공이후 사용자정보를 가져올 때의 설정들을 담당
                .oauth2Login()
                .userInfoEndpoint().userService(cutsomOAuth2UserService);
    }
}
