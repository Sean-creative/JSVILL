package com.sjs.jsvill.controller.home;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class CtlApiAppHome {

    @RequestMapping("/app/home")
    public String appHome() {
        log.info("user home");
        return "/app/home";
    }


}
