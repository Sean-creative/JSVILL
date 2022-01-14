package com.sjs.jsvill.controller;

import com.sjs.jsvill.dto.GroupDTO;
import com.sjs.jsvill.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/groupManagement/group")
@Log4j2
@RequiredArgsConstructor
public class GroupController {

    private final GroupService service;

    @RequestMapping("edit")
    public void edit() {
        log.info("editgroup");
    }

    @RequestMapping("register")
    public void register() {
        log.info("registergroup");
    }
    @PostMapping("register")
    public String registerPost(GroupDTO dto, RedirectAttributes redirectAttributes) {
        log.info("dto..." + dto);

        //일단은 타입과 멤버는 이걸로 고정
        dto.setGroupType_rowid(1L);

        Long gno = service.register(dto);
        log.info("result", gno);
        return "redirect:/groupManagement/manage";
    }
}
