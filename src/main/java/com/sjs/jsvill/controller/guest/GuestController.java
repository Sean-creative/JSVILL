package com.sjs.jsvill.controller.guest;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/guest")
@RequiredArgsConstructor
public class GuestController {
    //입실 시 가이드
    @GetMapping("/guestGuide1")
    public String guestGuide1() {
        return "/guest/guestGuide1.html";
    }
    @GetMapping("/guestGuide2")
    public String guestGuide2() {
        return "/guest/guestGuide2.html";
    }
    @GetMapping("/guestGuide3")
    public String guestGuide3() {
        return "/guest/guestGuide3.html";
    }
    @GetMapping("/guestGuide4")
    public String guestGuide4() {
        return "/guest/guestGuide4.html";
    }

    //퇴실 시 가이드
    @GetMapping("/guestGuide5")
    public String guestGuide5() {
        return "/guest/guestGuide5.html";
    }

}
