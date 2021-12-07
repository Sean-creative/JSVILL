package com.sjs.jsvill.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/groupManagement/group")
@Log4j2
@RequiredArgsConstructor
public class GroupController {

    @RequestMapping("edit")
    public void edit() {
        log.info("editgroup");
    }

    @RequestMapping("register")
    public void register() {
        log.info("registergroup");
    }
}
