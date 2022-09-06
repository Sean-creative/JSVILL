package com.sjs.jsvill.controller.member;

import com.sjs.jsvill.dto.member.MemberLoginDTO;
import com.sjs.jsvill.dto.member.MemberUserLoginDTO;
import com.sjs.jsvill.service.member.MemberUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class CtlMemberLogin {

    private final MemberUserService memberUserService;

    @ResponseBody
    @GetMapping("/member/login/{phone}")
    public MemberUserLoginDTO action(@PathVariable("phone") String phone, @RequestBody MemberLoginDTO memberLogin) {
        MemberUserLoginDTO memberUser = memberUserService.login(phone, memberLogin);
        if(memberUser==null) { //memberUser가 없다면, 빈 MemberUser 객체를 만들기
            memberUser = new MemberUserLoginDTO();
        }
       return memberUser;
    }

    //로그인
    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    //휴대폰번호 인증
    @GetMapping("/signup/1")
    public String signup1() {
//        Long memberUserR = memberUserService.register(dto);
        return "member/signup1";
    }

    @GetMapping("/signup/2")
    public String signup2() {
        return "member/signup2";
    }

    @GetMapping("/signup/3")
    public String signup3() {
        return "member/signup3";
    }

    @GetMapping("/signup/4")
    public String signup4() {
        return "member/signup4";
    }

}
