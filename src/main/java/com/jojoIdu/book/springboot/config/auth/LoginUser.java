/*
메서드 인자로 세션값을 바로 받을수 있도록 어노테이션 생성
 */
package com.jojoIdu.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//이 어노테이션이 생성될 수 있는 위치 지정
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
//@interface : 어노테이션 클래스로 지정함
public @interface LoginUser {
}
