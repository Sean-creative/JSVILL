package com.sjs.jsvill.controller.jimmy.notice;

import com.sjs.jsvill.dto.jimmy.notice.NoticeDTO;
import com.sjs.jsvill.service.jimmy.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log4j2
@RequiredArgsConstructor
public class CtlApiNoticeWp {

    private final NoticeService noticeService;

    @RequestMapping("/notice/wp")
    @ResponseBody
    public Long appNoticeWp(@RequestBody NoticeDTO dto) {
        log.info("user notice");

        return noticeService.register(dto);
    }


}
