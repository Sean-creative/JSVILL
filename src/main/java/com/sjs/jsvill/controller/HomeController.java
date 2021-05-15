package com.sjs.jsvill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
//메인 컨트롤러
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("registerUnit")
    public String registerUnit() {
        return "registerUnit";
    }
}
