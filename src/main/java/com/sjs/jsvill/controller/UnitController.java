package com.sjs.jsvill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UnitController {

    @RequestMapping("registerUnit")
    public String registerUnit() {
        return "buildingManagement/registerUnit";
    }

    @RequestMapping("communityWrite")
    public String communityWrite() {
        return "community/communityWrite";
    }
}
