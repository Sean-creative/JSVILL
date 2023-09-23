package com.sjs.jsvill.controller.group;

import com.sjs.jsvill.dto.GroupDTO;
import com.sjs.jsvill.dto.member.MemberDTO;
import com.sjs.jsvill.service.group.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/group")
@Log4j2
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    @GetMapping("/register")
    public String register() {
        return "group/register";
    }

    @PostMapping("/register")
    public String register(GroupDTO groupDTO, @AuthenticationPrincipal MemberDTO memberDTO) {
        //일단 멤버는 이걸로 고정;
        log.info("dto..." + groupDTO);

        Long gno = groupService.register(groupDTO, memberDTO.getMemberRowid());
        log.info("result", gno);
        return "redirect:/group/list";
    }
    @GetMapping("/list")
    public String list(@AuthenticationPrincipal MemberDTO memberDTO, Model model) {
        model.addAttribute("result", groupService.getList(memberDTO.getMemberRowid()));
        return "group/list";
    }

    @GetMapping("/edit")
    public String edit(long groupRowid, Model model) {
        log.info("groupRowid: " + groupRowid);
        //TODO 해당 그룹의 정보를 전달해줘야함 jpa-read로 쉽게 전달할 수 있을듯?
        model.addAttribute("result" , groupService.get(groupRowid));
        return "group/edit";
    }
    @PostMapping("/edit")
    public String edit(GroupDTO groupDTO, RedirectAttributes redirectAttributes){
        log.info("groupDTO : " + groupDTO);
        groupService.modify(groupDTO);
        return "redirect:/group/list";
    }



    @PostMapping("/remove")
    public String remove(long groupRowid, RedirectAttributes redirectAttributes){
        log.info("groupRowid: " + groupRowid);
        log.info("??? : " + groupService.remove(groupRowid));
        redirectAttributes.addFlashAttribute("msg", groupRowid);
        return "redirect:/group/list";
    }
}
