package com.sjs.jsvill.controller;

import com.sjs.jsvill.dto.member.MemberDTO;
import com.sjs.jsvill.entity.Reminder;
import com.sjs.jsvill.service.kafka.NotiMessage;
import com.sjs.jsvill.service.reminder.member.ReminderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
public class HomeController {

    private final ReminderService reminderService;

    @GetMapping({ "/", "/home"})
    public String home(@AuthenticationPrincipal MemberDTO memberDTO, Model model) {


        List<Reminder> reminders = reminderService.findAllRemindersByMember(memberDTO.getMemberRowid());
        List<NotiMessage> notiMessages = new ArrayList<>();
        reminders.forEach(reminder -> {
            notiMessages.add(NotiMessage.builder().userPhone(reminder.getMember().getPhoneNumber()).contents(reminder.getContents()).daysAgo(reminder.getDaysAgo()).build());
        });

        model.addAttribute("notificationsList", notiMessages);
        return "home/home";
    }
}
