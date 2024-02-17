package com.sjs.jsvill.service;

import com.sjs.jsvill.dto.GroupDTO;
import com.sjs.jsvill.entity.Reminder;
import com.sjs.jsvill.service.group.GroupService;
import com.sjs.jsvill.service.reminder.member.ReminderService;
import com.sjs.jsvill.util.Json;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class ReminderServiceTests {
    @Autowired
    private ReminderService reminderService;
    @Test
    public void findAllRemindersByMember() {
        Json.stringToJson(reminderService.findAllRemindersByMember(1L), "ReminderServiceTests - findAllRemindersByMember");
    }
    @Test
    public void deleteAllByMember() {
        reminderService.deleteAllByMember(1L);
    }
}
