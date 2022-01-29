package com.sjs.jsvill.controller;

import com.sjs.jsvill.dto.UnitDTO;
import com.sjs.jsvill.service.UnitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/unit")
@Log4j2
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;

    @GetMapping("register")
    public void register(int group_rowid, Model model) {
        log.info("group_rowid : " + group_rowid);
        model.addAttribute("group_rowid", group_rowid);
    }

    @PostMapping("register")
    public String register(UnitDTO dto, RedirectAttributes redirectAttributes) {
        log.info("dto..." + dto);

        //TODO 로그인이 유지가 되면 컨트롤러에서 넣어줘도 상관없음

        Long gno = unitService.register(dto);
        log.info("result", gno);
        return "redirect:/group/list";

    }

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
