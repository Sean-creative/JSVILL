package com.sjs.jsvill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String home() {
        return "main/main";
    }

    @RequestMapping("manageBuilding")
    public String manageBuilding(Model model) {
        model.addAttribute("name","sean님 환영합니다!");
        return "buildingManagement/manageBuilding";
    }

    @RequestMapping("editBuilding")
    public String editBuilding() {
        return "buildingManagement/editBuilding";
    }

    @RequestMapping("registerBuilding")
    public String registerBuilding() {
        return "buildingManagement/registerBuilding";
    }

    @RequestMapping("registerUnit")
    public String registerUnit() {
        return "buildingManagement/registerUnit";
    }

    @RequestMapping("community")
    public String community() {
        return "community/community";
    }

    @RequestMapping("communityWrite")
    public String communityWrite() {
        return "community/communityWrite";
    }


}
