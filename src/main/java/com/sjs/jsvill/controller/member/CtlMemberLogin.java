package com.sjs.jsvill.controller.member;

import com.sjs.jsvill.dto.member.SignUpPinNewDTOReq;
import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.service.member.MemberService;
import com.sjs.jsvill.service.util.SmsService;
import com.sjs.jsvill.util.Json;
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
//    public MemberUserLoginDTO action(@PathVariable("phoneNumber") String phone, @RequestBody MemberLoginDTO memberLogin) {
//        MemberUserLoginDTO memberUser = memberUserService.login(phone, memberLogin);
//        if(memberUser==null) { //memberUser가 없다면, 빈 MemberUser 객체를 만들기
//            memberUser = new MemberUserLoginDTO();
//        }
//       return memberUser;
//    }

    //로그인
    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/signUpNew")
    public String signUpNew() {
//        Long memberUserR = memberUserService.register(dto);
        return "member/signUpNew";
    }
    @PostMapping("/signUpNew")
    public String signUpNew(HttpSession session, String phoneNumber, RedirectAttributes attributes) {
        Optional<Member> member = memberService.findByPhoneNumber(phoneNumber);

        // 이미 가입된 전화번호 있음 -> 안돼 돌아가
        if(member.isPresent()) {
            //flash를 사용하기 위해서, 일단 redirect 처리로 해결
            attributes.addFlashAttribute("phoneNumber", member.get().getPhoneNumber());
            return "redirect:/member/signUpNew";
        }
        // 이미 가입된 번호 없음 -> 새로 가입
        else {
            String code;
            try {
                code = smsService.sendRandomMessage(phoneNumber.replaceAll("-", ""));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            session.setAttribute("rand", code);
            session.setAttribute("phoneNumber", phoneNumber);
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
        String rand = (String) session.getAttribute("rand");
        System.out.println("authCode : " + authCode);
        System.out.println("rand : " + rand);

        if (rand!=null&&rand.equals(authCode)) {
            session.removeAttribute("rand");
            return "redirect:/member/signUpForm";
        } else {
            attributes.addFlashAttribute("wrong", true);
            return "redirect:/member/signUpAuth";
        }
    }
    @GetMapping("/signUpForm")
    public String signUpForm(Model model) {
        model.addAttribute("req", new SignUpPinNewDTOReq());
        return "member/signUpForm";
    }
    @PostMapping("/signUpForm")
    public String signUpForm(HttpSession session, @ModelAttribute("req") @Valid SignUpPinNewDTOReq req, BindingResult result) {
        if (result.hasErrors()) {
            return "member/signUpForm";
        }
        Json.stringToJson(req, "post-signUpForm");
        String phoneNumber = (String) session.getAttribute("phoneNumber");
        System.out.println("phoneNumber : " + phoneNumber);

        //TODO 트랜잭션 같은거 걸어야 하나?
        req.setPhoneNumber(phoneNumber);
        memberService.register(req);

        session.removeAttribute("phoneNumber");
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
