package com.sjs.jsvill.controller.member;

import com.sjs.jsvill.entity.MemberUser;
import com.sjs.jsvill.service.member.MemberUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log4j2
@RequiredArgsConstructor
public class CtlMemberLogin {


    @ResponseBody
    @RequestMapping("/member/login/{phone}")
    public boolean action(@PathVariable("phone") String phone) {
       return false;
    }
}
