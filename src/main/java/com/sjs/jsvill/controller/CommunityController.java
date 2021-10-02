package com.sjs.jsvill.controller;

import com.sjs.jsvill.dto.PageRequestDTO;
import com.sjs.jsvill.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community")
@Log4j2
@RequiredArgsConstructor
public class CommunityController {

//    private final GuestbookService service; //final로 선언

    @GetMapping("/community")
    public void community() {log.info("community");}

    @GetMapping("/read")
    public void read() {log.info("read");}

    @GetMapping("/register")
    public void register() {log.info("register");}


//    @GetMapping("/community")
//    public void list(PageRequestDTO pageRequestDTO, Model model){
//        log.info("list.............." + pageRequestDTO);
//        log.info("service.getList(pageRequestDTO).............." + service.getList(pageRequestDTO));
//        model.addAttribute("result", service.getList(pageRequestDTO));
//    }
}
