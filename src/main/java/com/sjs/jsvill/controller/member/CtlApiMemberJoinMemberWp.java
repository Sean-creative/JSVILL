package com.sjs.jsvill.controller.member;

import com.sjs.jsvill.dto.MemberDTO;
import com.sjs.jsvill.service.member.MemberAdminService;
import com.sjs.jsvill.service.member.MemberService;
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
public class CtlApiMemberJoinMemberWp {

    private final MemberService memberService;

    @ResponseBody
    @RequestMapping("/member/join/member/wp")
    public Long action(@RequestBody MemberDTO dto) {
        return memberService.register(dto);
    }
}
