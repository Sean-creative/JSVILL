package com.sjs.jsvill.controller.notice;

import com.sjs.jsvill.dto.notice.NoticeResDTO;
import com.sjs.jsvill.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
public class CtlApiNotice {
    private final NoticeService noticeService;

    @RequestMapping("/notice")
    @ResponseBody
    public List<NoticeResDTO> appNotice() {
        log.info("user notice");
        return noticeService.getNoticeList();
    }


}
