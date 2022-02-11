package com.sjs.jsvill.controller.notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class CtlApiNotice {

    @RequestMapping("/notice")
    public String appNotice() {
        log.info("user notice");
        return "/app/notice";
    }


}
