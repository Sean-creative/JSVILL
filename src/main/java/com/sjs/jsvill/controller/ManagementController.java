package com.sjs.jsvill.controller;

import com.sjs.jsvill.dto.GroupManageResDTO;
import com.sjs.jsvill.service.group.registergroup.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/groupManagement")
@Log4j2
@RequiredArgsConstructor
public class ManagementController {

    private final GroupService service;

    @GetMapping("manage")
    public void manage(Model model) {

        model.addAttribute("name", "sean님 환영합니다!");
        model.addAttribute("result", new GroupManageResDTO());
    }

    @GetMapping("residents")
    public void residents() {
        log.info("residents");
    }
}
