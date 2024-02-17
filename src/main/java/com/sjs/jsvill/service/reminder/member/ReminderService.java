package com.sjs.jsvill.service.reminder.member;

import com.sjs.jsvill.entity.Reminder;

import java.util.List;

public interface ReminderService {
    Reminder createReminder(Reminder reminder);
    List<Reminder> findAllReminders();
    List<Reminder> findAllRemindersByMember(Long memberRowid);
    Reminder updateReminder(Long id, Reminder reminderDetails);
    void deleteReminder(Long id);
    void deleteAllByMember(Long memberRowid);
}
