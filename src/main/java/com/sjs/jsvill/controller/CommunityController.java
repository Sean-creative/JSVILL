package com.sjs.jsvill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommunityController {

    @RequestMapping("community")
    public String community() {
        return "community/community";
    }

}
