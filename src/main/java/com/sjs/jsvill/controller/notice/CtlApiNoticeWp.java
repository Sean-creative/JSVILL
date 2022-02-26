package com.sjs.jsvill.controller.notice;

import com.sjs.jsvill.dto.notice.NoticeDTO;
import com.sjs.jsvill.repository.NoticeRepository;
import com.sjs.jsvill.service.notice.NoticeService;
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
