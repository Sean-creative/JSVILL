package com.sjs.jsvill.controller.notice;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class CtlApiNoticeV {

    @RequestMapping("/notice/v")
    public String appNoticeV() {
        log.info("user noticeDetail");
        return "/app/noticeDetail";
    }


}
