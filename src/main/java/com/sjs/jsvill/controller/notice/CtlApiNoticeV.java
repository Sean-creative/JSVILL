package com.sjs.jsvill.controller.notice;

import com.sjs.jsvill.dto.notice.NoticeResDTO;
import com.sjs.jsvill.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log4j2
@RequiredArgsConstructor
public class CtlApiNoticeV {

    private final NoticeService noticeService;

    @RequestMapping("/notice/v")
    @ResponseBody
    public NoticeResDTO appNoticeV(String noticeR) {
        log.info("user noticeDetail");
        return noticeService.get(noticeR);
    }
}
