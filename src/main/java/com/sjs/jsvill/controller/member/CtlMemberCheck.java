package com.sjs.jsvill.controller.member;

import com.sjs.jsvill.dto.member.MemberUserDTO;
import com.sjs.jsvill.entity.Member;
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
public class CtlMemberCheck {

    private final MemberService memberService;

    @ResponseBody
    @RequestMapping("/member/check/{phone}")
    public boolean action(@PathVariable("phone") String phone) {
        boolean result = false;
        Member member = memberService.get(phone);
        if(member!=null) result = true;
        return result;
    }
}
