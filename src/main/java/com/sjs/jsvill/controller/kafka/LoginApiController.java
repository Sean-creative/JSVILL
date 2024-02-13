package com.sjs.jsvill.controller.kafka;

import com.sjs.jsvill.dto.member.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
//로그인 관련 컨트롤러
public class LoginApiController {

    @GetMapping("/login-status")
    //로그인 체크하는거니까 이api는 로그인을 하지 않아도 체크할 수 있어야 함
    public boolean isLogin(@AuthenticationPrincipal MemberDTO memberDTO) {
        return memberDTO != null;
    }
}
