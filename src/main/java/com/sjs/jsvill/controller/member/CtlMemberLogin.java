package com.sjs.jsvill.controller.member;

import com.sjs.jsvill.dto.member.MemberLoginDTO;
import com.sjs.jsvill.entity.MemberUser;
import com.sjs.jsvill.service.member.MemberUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log4j2
@RequiredArgsConstructor
public class CtlMemberLogin {

    private final MemberUserService memberUserService;

    @ResponseBody
    @RequestMapping("/member/login/{phone}")
    public MemberUser action(@PathVariable("phone") String phone, @RequestBody MemberLoginDTO memberLogin) {
        MemberUser memberUser = memberUserService.login(phone, memberLogin);
        if(memberUser==null) { //memberUser가 없다면, 빈 MemberUser 객체를 만들기
            memberUser = new MemberUser();
        }
       return memberUser;
    }
}
