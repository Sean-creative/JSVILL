package com.sjs.jsvill.controller.member;

import com.sjs.jsvill.dto.MemberDTO;
import com.sjs.jsvill.service.member.MemberAdminService;
import com.sjs.jsvill.service.member.MemberService;
import com.sjs.jsvill.service.member.MemberUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class CtlApiMemberJoinWp {

    private final MemberService memberService;
    private final MemberAdminService memberAdminService;
    private final MemberUserService memberUserService;

    @RequestMapping("/member/join/wp")
    public String action(MemberDTO dto) {
        memberService.register(dto);
        if(dto.get_memberType_rowid()==10L) { //집주인이면
            //memberAdminService.register()
        }else { //세입자라면
            //memberUserService.register()
        }
        return "";
    }
}
