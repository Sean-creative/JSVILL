package com.sjs.jsvill.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/buildingManagement/unit")
@Log4j2
@RequiredArgsConstructor
public class UnitController {

    @RequestMapping("register")
    public void register() {log.info("register");}

    @PostMapping("register")
    public void register(String str) {log.info("register - post : " + str);}

    @RequestMapping("edit")
    public void edit() {log.info("edit");}

    @RequestMapping("communityWrite")
    public void communityWrite() {log.info("communityWrite");}

    @RequestMapping("detail")
    public void detail(int uno) {log.info("uno: " + uno);}

    @GetMapping("/previousContract")
    public void previousContract() {log.info("previousContract");}

    @GetMapping("/contractRegister")
    public void contractRegister() {log.info("contractRegister");}

    @GetMapping("/contractEdit")
    public void contractEdit() {log.info("contractEdit");}
}
