package com.sjs.jsvill.service.reminder.member;

import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity.Reminder;
import com.sjs.jsvill.repository.ReminderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository reminderRepository;

    @Override
    public Reminder createReminder(Reminder reminder) {
        // reminderRepository를 사용하여 새로운 Reminder를 저장하고 반환합니다.
        return reminderRepository.save(reminder);
    }

    @Override
    public List<Reminder> findAllReminders() {
        // reminderRepository를 사용하여 모든 Reminder를 조회하여 반환합니다.
        return reminderRepository.findAll();
    }

    @Override
    public List<Reminder> findAllRemindersByMember(Long memberRowid) {
        // reminderRepository를 사용하여 주어진 id에 해당하는 Reminder를 조회하여 반환합니다.
        return reminderRepository.findAllByMember_MemberRowid(memberRowid);
    }

    @Override
    public Reminder updateReminder(Long id, Reminder reminderDetails) {
        // reminderRepository를 사용하여 주어진 id에 해당하는 Reminder를 조회합니다.
        Reminder existingReminder = reminderRepository.findById(id)
                .orElse(null);
        if (existingReminder == null) {
            return null; // 수정할 Reminder가 없을 경우 null 반환
        }

        // reminderDetails로 받은 정보로 기존 Reminder를 업데이트합니다.
//        existingReminder.setTitle(reminderDetails.getTitle());
//        existingReminder.setDescription(reminderDetails.getDescription());
//        existingReminder.setDateTime(reminderDetails.getDateTime());

        // 업데이트된 Reminder를 저장하고 반환합니다.
        return reminderRepository.save(existingReminder);
    }

    @Override
    public void deleteReminder(Long id) {
        // reminderRepository를 사용하여 주어진 id에 해당하는 Reminder를 삭제합니다.
        reminderRepository.deleteById(id);
    }
}
