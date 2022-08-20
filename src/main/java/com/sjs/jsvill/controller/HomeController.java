package com.sjs.jsvill.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class HomeController {

    @RequestMapping({"/", "home"})
    public String home() {
        log.info("home....");
        return "home/home";
    }
}
