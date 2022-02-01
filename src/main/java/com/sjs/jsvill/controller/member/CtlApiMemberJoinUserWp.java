package com.sjs.jsvill.controller.member;

import com.sjs.jsvill.dto.MemberDTO;
import com.sjs.jsvill.dto.member.MemberUserDTO;
import com.sjs.jsvill.service.member.MemberAdminService;
import com.sjs.jsvill.service.member.MemberService;
import com.sjs.jsvill.service.member.MemberUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log4j2
@RequiredArgsConstructor
public class CtlApiMemberJoinUserWp {

    private final MemberUserService memberUserService;

    @ResponseBody
    @RequestMapping("/member/join/user/wp")
    public Long action(@RequestBody MemberUserDTO dto) {
        Long memberUserR = memberUserService.register(dto);
        return memberUserR;
    }
}
