package com.sjs.jsvill.controller;

import com.sjs.jsvill.dto.GroupDTO;
import com.sjs.jsvill.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("group")
@Log4j2
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @RequestMapping("edit")
    public void edit() {
        log.info("editgroup");
    }

    @GetMapping("register")
    public void register() {
        log.info("registergroup");
    }

    @PostMapping("register")
    public String registerPost(GroupDTO dto, RedirectAttributes redirectAttributes) {
        //일단 멤버는 이걸로 고정;
        log.info("dto..." + dto);

        Long gno = groupService.register(dto);
        log.info("result", gno);
            return "redirect:/group/list";
    }

    @GetMapping("list")
    public void list(Model model) {
        model.addAttribute("result", groupService.getList(1L));
    }

    @PostMapping("/remove")
    public String remove(long group_rowid, RedirectAttributes redirectAttributes){

        log.info("group_rowid: " + group_rowid);
        log.info("??? : " + groupService.remove(group_rowid));
        redirectAttributes.addFlashAttribute("msg", group_rowid);
        return "redirect:/group/list";
    }
}
