package com.sjs.jsvill.controller.member;

import com.sjs.jsvill.dto.member.SignUpPinNewDTOReq;
import com.sjs.jsvill.dto.member.SignUpPinOldDTOReq;
import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.service.member.MemberService;
import com.sjs.jsvill.service.sms.NaverSmsService;
import com.sjs.jsvill.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    private final NaverSmsService naverSmsService;

    //로그인
    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/phoneAuthNew")
    public String phoneAuthNew() {
        return "member/phoneAuthNew";
    }
    @PostMapping("/phoneAuthNew")
    public String phoneAuthNew(HttpSession session, String phoneNumber, RedirectAttributes attributes) {
        Optional<Member> member = memberService.findByPhoneNumber(phoneNumber);

        // 이미 가입된 전화번호 있음 -> 안돼 돌아가
        if(member.isPresent()) {
            attributes.addFlashAttribute("phoneNumber", member.get().getPhoneNumber());
            return "redirect:/member/phoneAuthNew";
        }
        // 이미 가입된 번호 없음 -> 새로 가입
        else {
            String code;
            try {
                code = naverSmsService.sendRandomMessage(phoneNumber.replaceAll("-", ""));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            session.setAttribute("rand", code);
            session.setAttribute("phoneNumber", phoneNumber);
            session.setAttribute("from", "phoneAuthNew");
            return "redirect:/member/phoneAuthCheck";
        }
    }

    @GetMapping("/phoneAuthCheck")
    public String phoneAuthCheck() {return "member/phoneAuthCheck";}
    @PostMapping("/phoneAuthCheck")
    public String authCheck(HttpSession session, String authCode, RedirectAttributes attributes) {
        //TODO 인증번호 검사하는거 좀 더 정교하게
        String rand = (String) session.getAttribute("rand");
        System.out.println("authCode : " + authCode);
        System.out.println("rand : " + rand);
        String from = (String) session.getAttribute("from");
        System.out.println("from : " + from);
        if(rand==null||from==null) return "member/login";

        if (rand.equals(authCode)) {
            session.removeAttribute("rand");
            session.removeAttribute("from");
            if(from.equals("phoneAuthNew")) return "redirect:/member/signUpPinNew";
            else if(from.equals("phoneAuthOld")) return "redirect:/member/signUpPinOld";
            else return "redirect:/home";
        } else {
            attributes.addFlashAttribute("wrong", true);
            return "redirect:/member/phoneAuthCheck";
        }
    }
    @GetMapping("/signUpPinNew")
    public String signUpPinNew(Model model) {
        model.addAttribute("req", new SignUpPinNewDTOReq());
        return "member/signUpPinNew";
    }
    @PostMapping("/signUpPinNew")
    public String signUpPinNew(HttpSession session, @ModelAttribute("req") @Valid SignUpPinNewDTOReq req, BindingResult result) {
        if (result.hasErrors()) {
            return "member/signUpPinNew";
        }
        Json.stringToJson(req, "post-signUpPinNew");
        String phoneNumber = (String) session.getAttribute("phoneNumber");
        System.out.println("phoneNumber : " + phoneNumber);

        if(phoneNumber==null) return "member/login";

        req.setPhoneNumber(phoneNumber);
        memberService.register(req);

        session.removeAttribute("phoneNumber");
        return "member/login";
    }

    @GetMapping("/phoneAuthOld")
    public String phoneAuthOld() {
        return "member/phoneAuthOld";
    }
    @PostMapping("/phoneAuthOld")
    public String phoneAuthOld(HttpSession session, String phoneNumber, RedirectAttributes attributes) {
        Optional<Member> member = memberService.findByPhoneNumber(phoneNumber);

        // 이미 가입된 전화번호 없음 -> 안돼 돌아가
        if(!member.isPresent()) {
            //flash를 사용하기 위해서, 일단 redirect 처리로 해결
            attributes.addFlashAttribute("phoneNumber", phoneNumber);
            return "redirect:/member/phoneAuthOld";
        }
        // 이미 가입된 번호 있음 -> 폰번호 찾기
        else {
            String code;
            try {
                code = naverSmsService.sendRandomMessage(phoneNumber.replaceAll("-", ""));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            session.setAttribute("rand", code);
            session.setAttribute("phoneNumber", phoneNumber);
            session.setAttribute("from", "phoneAuthOld");
            return "member/phoneAuthCheck";
        }
    }

    @GetMapping("/signUpPinOld")
    public String signUpPinOld() {
        return "member/signUpPinOld";
    }

    @PostMapping("/signUpPinOld")
    public String signUpPinOld(HttpSession session, @ModelAttribute("req") @Valid SignUpPinOldDTOReq req, BindingResult result) {
        if (result.hasErrors()) {
            return "member/signUpPinOld";
        }
        Json.stringToJson(req, "post-signUpPinNew");
        String phoneNumber = (String) session.getAttribute("phoneNumber");
        System.out.println("phoneNumber : " + phoneNumber);

        if(phoneNumber==null) return "member/login";

        req.setPhoneNumber(phoneNumber);
        memberService.modify(req);

        session.removeAttribute("phoneNumber");
        return "member/login";
    }
}
