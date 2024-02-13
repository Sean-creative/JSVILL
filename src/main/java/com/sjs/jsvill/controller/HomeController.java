package com.sjs.jsvill.controller;

import com.sjs.jsvill.dto.NotiMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
public class HomeController {

    @GetMapping({ "/", "/home"})
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                log.info("권한: " + authority.getAuthority());
            }
        }
        List<NotiMessageDTO> notificationsList = List.of(
                NotiMessageDTO.builder().userPhone("010-5007-0615").message("hehehe").createdHours("1").build(),
                NotiMessageDTO.builder().userPhone("010-5007-0617").message("zzzz").createdHours("2").build(),
                NotiMessageDTO.builder().userPhone("010-5007-069").message("eeee").createdHours("3").build()
        );

        model.addAttribute("notificationsList", notificationsList);
        return "home/home";
    }
}
