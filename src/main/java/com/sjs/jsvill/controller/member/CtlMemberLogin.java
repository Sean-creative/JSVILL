package com.sjs.jsvill.controller.member;

import com.sjs.jsvill.dto.member.SignUpPinNewDTOReq;
import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.service.member.MemberService;
import com.sjs.jsvill.service.util.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

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
    @PostMapping("/signUpNew")
    public String signUpNew(HttpSession session, String phone, RedirectAttributes attributes) {
        Optional<Member> member = memberService.findByPhoneNumber(phone);

        // 이미 가입된 전화번호 있음 -> 안돼 돌아가
        if(member.isPresent()) {
            //flash를 사용하기 위해서, 일단 redirect 처리로 해결
            attributes.addFlashAttribute("phone", member.get().getPhoneNumber());
            return "redirect:/member/signUpNew";
        }
        // 이미 가입된 번호 없음 -> 새로 가입
        else {
            String code;
            try {
                code = smsService.sendRandomMessage(phone.replaceAll("-", ""));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            session.setAttribute("rand", code);
            return "member/signUpAuth";
        }
    }

    @GetMapping("/signUpAuth")
    public String signUpAuth() {
        return "member/signUpAuth";
    }
    @PostMapping("/signUpAuth")
    public String authCheck(HttpSession session, String authCode, RedirectAttributes attributes) {
        //TODO 인증번호 검사하는거 좀 더 정교하게
        //들어온 인증번호가 맞으면 -> 회원가입
        //틀리면 -> 다시 입력해주세요
        System.out.println("authCode : " + authCode);
        String rand = (String) session.getAttribute("rand");
        System.out.println("rand : " + rand);
        System.out.println(rand + " : " + authCode);

        if (rand!=null&&rand.equals(authCode)) {
//            session.removeAttribute("rand");
            return "member/signUpPinNew";
        } else {
            attributes.addFlashAttribute("wrong", true);
            return "redirect:/member/signUpAuth";
        }
    }
    @GetMapping("/signUpPinNew")
    public String signUpPinNew(Model model) {
        model.addAttribute("req", new SignUpPinNewDTOReq());
        return "member/signUpPinNew";
    }
    @PostMapping("/signUpPinNew")
    public String signUpPinNew(@ModelAttribute("req") @Valid SignUpPinNewDTOReq req, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "member/signUpPinNew";
        }
        System.out.println("req.getPinNumber() : " + req.getPinNumber());
        return "member/login";
    }


    @GetMapping("/signUpOld")
    public String signUpOld() {
        return "member/signUpOld";
    }
    @PostMapping("/signUpOld")
    public String signUpOld(@Length(min = 4, max = 4, message = "zzzz") String phone, Model model) {
        System.out.println("Phone : " + phone);
        return "member/signUpAuth";
    }


    @GetMapping("/signUpPinOld")
    public String signUpPinOld() {
        return "member/signUpPinOld";
    }
}
