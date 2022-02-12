package com.sjs.jsvill.controller.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class CtlApiBoardW {

    @RequestMapping("/board/w")
    public String appBoardW() {
        log.info("user board w");
        return "/app/letter";
    }


}
