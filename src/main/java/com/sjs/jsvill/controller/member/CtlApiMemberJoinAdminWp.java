package com.sjs.jsvill.controller.member;

import com.sjs.jsvill.dto.member.MemberAdminDTO;
import com.sjs.jsvill.service.member.MemberAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log4j2
@RequiredArgsConstructor
public class CtlApiMemberJoinAdminWp {

    private final MemberAdminService memberAdminService;

    @ResponseBody
    @RequestMapping("/member/join/admin/wp")
    public Long action(@RequestBody MemberAdminDTO dto) {
        System.out.println(dto.getSaltId());
        System.out.println(dto.getMemberR());

        Long memberAdminR = memberAdminService.register(dto);
        return memberAdminR;
    }
}
