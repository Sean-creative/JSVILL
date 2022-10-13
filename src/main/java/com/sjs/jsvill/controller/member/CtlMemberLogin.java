package com.sjs.jsvill.controller.member;

import com.sjs.jsvill.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class CtlMemberLogin {

    private final MemberService memberService;

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
    @PostMapping("/signUpNew")
    public String signUpNew(String phone, Model model) {
        System.out.println("Phone : " + phone);
        return "member/signUpAuth";
    }

    @GetMapping("/signUpAuth")
    public String signUpAuth() {
        return "member/signUpAuth";
    }
    @PostMapping("/signUpAuth")
    public String signUpAuth(String vnum) {
        System.out.println("vnum : " + vnum);
        return "member/signUpPinNew";
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





//    @PostMapping("phoneAuth")
//    @ResponseBody
//    public Boolean phoneAuth(String tel) {
//        try { // 이미 가입된 전화번호가 있으면
//            if(memberService.memberTelCount(tel) > 0)
//                return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        String code = memberService.sendRandomMessage(tel);
//        session.setAttribute("rand", code);
//
//        return false;
//    }
//
//    @PostMapping("phoneAuthOk")
//    @ResponseBody
//    public Boolean phoneAuthOk() {
//        String rand = (String) session.getAttribute("rand");
//        String code = (String) request.getParameter("code");
//
//        System.out.println(rand + " : " + code);
//
//        if (rand.equals(code)) {
//            session.removeAttribute("rand");
//            return false;
//        }
//        return true;
//    }
}
