package com.example.service.auth;

import com.example.domain.member.Member;
import com.example.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /*
    사용자 아이디를 통해 UserDetails 객체를 반환
     */
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        System.out.println("여기 : " + userId);

        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException(userId + " : 해당 유저를 데이터베이스에서 찾을 수 없습니다."));

        log.info("CustomUserDetailsService.class / loadUserByUsername 매서드 : " + member.getUserId() + " 유저 찾음");

        return createUser(member);
    }

    /*
    로그인 아이디와 member 객체를 이용해 UserDetails 인터페이스의 구현체인 User 객체를 생성하여 반환
     */
    private User createUser(Member member){

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(member.getMemberAuthority().toString());

        grantedAuthorityList.add(simpleGrantedAuthority);

        return new User(member.getUserId(), member.getPwd(), grantedAuthorityList);
    }
}
