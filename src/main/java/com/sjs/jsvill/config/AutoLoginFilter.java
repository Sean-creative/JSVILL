package com.sjs.jsvill.config;

import com.sjs.jsvill.dto.member.MemberDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class AutoLoginFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
//        autoLogin();
        chain.doFilter(request, response);
    }

    private void autoLogin() {
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        //UserDetails 인터페이스를 구현한 클래스 여야 하는데, AuthenticationPrincipal가 MemberDTO라 User가 아닌, MemberDTO로 넣어줌
        MemberDTO memberDTO = new MemberDTO(1L, "010-5007-0615", "1730", authorities);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(memberDTO, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
