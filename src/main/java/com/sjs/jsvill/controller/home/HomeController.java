package com.sjs.jsvill.controller.home;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class HomeController {

    @GetMapping({ "/", "/home"})
    public String home(Model model) { //model이 있어야 AOP가 가능함
        return "home/home";
    }
}