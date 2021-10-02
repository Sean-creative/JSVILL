package com.sjs.jsvill.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buildingManagement/building")
@Log4j2
@RequiredArgsConstructor
public class BuildingController {

    @RequestMapping("edit")
    public void edit() {
        log.info("editBuilding");
    }

    @RequestMapping("register")
    public void register() {
        log.info("registerBuilding");
    }
}
