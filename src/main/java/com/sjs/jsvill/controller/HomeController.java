package com.sjs.jsvill.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class HomeController {

    @GetMapping({ "/home"})
    public String home() {
        log.info("home....");
        return "home/home";
    }

    @GetMapping({ "/"})
    public String login() {
        return "member/login";
    }
}
