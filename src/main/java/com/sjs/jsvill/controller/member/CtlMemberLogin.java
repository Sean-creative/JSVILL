package com.sjs.jsvill.controller.member;

import com.sjs.jsvill.service.member.MemberService;
import com.sjs.jsvill.service.util.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class CtlMemberLogin {

    private final MemberService memberService;
    private final SmsService smsService;

//    @ResponseBody
//    @GetMapping("/member/login/{phone}")
//    public MemberUserLoginDTO action(@PathVariable("phone") String phone, @RequestBody MemberLoginDTO memberLogin) {
//        MemberUserLoginDTO memberUser = memberUserService.login(phone, memberLogin);
//        if(memberUser==null) { //memberUser가 없다면, 빈 MemberUser 객체를 만들기
//            memberUser = new MemberUserLoginDTO();
//        }
//       return memberUser;
//    }

    //로그인
    @GetMapping("/login")
    public String login() {
        System.out.println("CtlMemberLogin-login-Get");
        return "member/login";
    }

    @GetMapping("/signUpNew")
    public String signUpNew() {
//        Long memberUserR = memberUserService.register(dto);
        return "member/signUpNew";
    }

    @PostMapping("/duplicateCheck")
    @ResponseBody
    public String duplicateCheck(@RequestParam(value = "phone") String phone) {
        return memberService.findByPhoneNumber(phone).getPhoneNumber();
    }
    @PostMapping("/signUpNew")
    public String signUpNew(HttpSession session, String phone) {
        String returnStr = "";
        String phoneExcludeHypen = phone.replaceAll("-", "");
        System.out.println("phoneExcludeHypen : " + phoneExcludeHypen);

        // 이미 가입된 전화번호가 있으면
        if(memberService.findByPhoneNumber(phone) == null) {}
        else {
            String code = null;
            try {
                code = smsService.sendRandomMessage(phoneExcludeHypen);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            session.setAttribute("rand", code);
        }

        return "member/signUpAuth";
    }

    @GetMapping("/signUpAuth")
    public String signUpAuth() {
        return "member/signUpAuth";
    }
    @PostMapping("/signUpAuth")
    @ResponseBody
    public Boolean signUpAuth(HttpSession session, @RequestParam(value = "authCode") String authCode) {
        Boolean result = false;
        System.out.println("authCode : " + authCode);
        String rand = (String) session.getAttribute("rand");
        System.out.println("rand : " + rand);
        System.out.println(rand + " : " + authCode);

        if (rand.equals(authCode)) {
            session.removeAttribute("rand");
            result = true;
        }
        
        //이처리가 비동기로 처리가 되어야함
        //뷰단에서 authCode와 rand가 맞는지 체크를하고 
        //1. 맞으면 -> PIN 번호 만들게끔, 2. 틀리면 -> 오류를 내뿜으면서 다시 작성하라고 해야합
        return result;
    }

    @GetMapping("/signUpPinNew")
    public String signUpPinNew() {
        return "member/signUpPinNew";
    }
    @PostMapping("/signUpPinNew")
    public String signUpPinNew(String pinNumber) {
        System.out.println("pinNumber : " + pinNumber);
        return "member/login";
    }


    @GetMapping("/signUpOld")
    public String signUpOld() {
        return "member/signUpOld";
    }
    @PostMapping("/signUpOld")
    public String signUpOld(String phone, Model model) {
        System.out.println("Phone : " + phone);
        return "member/signUpAuth";
    }


    @GetMapping("/signUpPinOld")
    public String signUpPinOld() {
        return "member/signUpPinOld";
    }
}
