package com.sjs.jsvill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
//메인 컨트롤러
//메인 컨트롤러2
    @GetMapping("/")
    public String home() {
        return "main/main";
    }

    @GetMapping("manageBuilding")
    public String manageBuilding() {
        return "buildingManagement/manageBuilding";
    }

    @GetMapping("editBuilding")
    public String editBuilding() {
        return "buildingManagement/editBuilding";
    }

    @GetMapping("registerBuilding")
    public String registerBuilding() {
        return "buildingManagement/registerBuilding";
    }

    @GetMapping("registerUnit")
    public String registerUnit() {
        return "buildingManagement/registerUnit";
    }

    @GetMapping("community")
    public String community() {
        return "community/community";
    }

    @GetMapping("communityWrite")
    public String communityWrite() {
        return "community/communityWrite";
    }


}
