package com.sjs.jsvill.controller.community;

import com.sjs.jsvill.service.community.CommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Log4j2
@Controller
@RequestMapping("/community")
@RequiredArgsConstructor /* Todo kjs @RequiredArgsConstructor 의 역할조사 */
public class CommunityController {
    /* Todo kjs @Autowired 로 Service 선언하는 방법과 private final 로 Service 선언하는 방식의 차이 */
    private final CommunityService communityService;

    @GetMapping("/community")
    public String community(Model model) {
        log.info("list >>>>> " + communityService.findAll());
        model.addAttribute("list", communityService.findAll());

        return "/community/community.html";
    }

}
