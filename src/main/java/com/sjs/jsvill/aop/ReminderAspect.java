package com.sjs.jsvill.aop;

import com.sjs.jsvill.dto.member.MemberDTO;
import com.sjs.jsvill.entity.Reminder;
import com.sjs.jsvill.service.kafka.ReminderMessage;
import com.sjs.jsvill.service.reminder.member.ReminderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Slf4j
@Component
@RequiredArgsConstructor
public class ReminderAspect {
    private final ReminderService reminderService;

    @Around("execution(String com.sjs.jsvill..*Controller.*(..)) && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object reminderApiAop(ProceedingJoinPoint joinPoint) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof MemberDTO) {
                Long memberRowid = ((MemberDTO) principal).getMemberRowid();
                List<Reminder> reminders = reminderService.findAllRemindersByMember(memberRowid);
                List<ReminderMessage> reminderMessages = new ArrayList<>();
                reminders.forEach(reminder -> {
                    reminderMessages.add(ReminderMessage.builder().userPhone(reminder.getMember().getPhoneNumber()).contents(reminder.getContents()).daysAgo(reminder.getDaysAgo()).build());
                });

                Object[] args = joinPoint.getArgs();
                // 메서드 인자 중 Model 타입을 찾아서 reminderList를 추가
                for (Object arg : args) {
                    if (arg instanceof Model model) {
                        model.addAttribute("reminderList", reminderMessages);
                    }
                }
            }
        }
        return joinPoint.proceed();
    }
}