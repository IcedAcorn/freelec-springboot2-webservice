/*
구글 로그인 이후 가져온 사용자의 정보를 기반으로
가입 및 정보수정, 세션 저장 등의 기능을 지원
 */
package com.jojoIdu.book.springboot.config.auth;

import com.jojoIdu.book.springboot.config.auth.dto.OAuthAttributes;
import com.jojoIdu.book.springboot.config.auth.dto.SessionUser;
import com.jojoIdu.book.springboot.domain.user.User;
import com.jojoIdu.book.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.h2.engine.Session;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CutsomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //현재 로그인 진행중인 서비스를 구분하는 코드/ 네이버로그인인지, 구글로그인인지..등..
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        //OAuth2 로그인 진행시 키가되는 필드값, Primary key와 같은 의미 (구글 :sub)
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        //OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);

        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes){
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getNameAttributeKey(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
