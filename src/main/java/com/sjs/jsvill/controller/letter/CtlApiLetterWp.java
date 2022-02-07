package com.sjs.jsvill.controller.letter;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class CtlApiLetterWp {

    @RequestMapping("/letter/wp")
    public String appHome() {
        log.info("user letter");
        return "/app/letter";
    }


}
