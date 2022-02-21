package com.sjs.jsvill.service.notice;

import com.sjs.jsvill.dto.notice.NoticeDTO;
import com.sjs.jsvill.dto.notice.NoticeResDTO;
import com.sjs.jsvill.entity.Notice;
import com.sjs.jsvill.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;

    @Override
    public Long register(NoticeDTO dto) {
        return noticeRepository.save(dtoToEntity(dto)).getNotice_rowid();
    }

    @Override
    public NoticeResDTO get(String noticeR) {
        return entityToDto(noticeRepository.findByNotice_rowid(Long.valueOf(noticeR)));
    }

    @Override
    public List<NoticeResDTO> getNoticeList() {
        return dtoListToEntity(noticeRepository.findAll());
    }
}
