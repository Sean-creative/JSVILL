package com.sjs.jsvill.service.jimmy.notice;

import com.sjs.jsvill.dto.jimmy.notice.NoticeDTO;
import com.sjs.jsvill.dto.jimmy.notice.NoticeResDTO;
import com.sjs.jsvill.repository.jimmy.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

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
        //주석해둠 - sean
//        return entityToDto(noticeRepository.findByNotice_rowid(Long.valueOf(noticeR)));
        return new NoticeResDTO();
    }

    @Override
    public List<NoticeResDTO> getNoticeList() {
        return dtoListToEntity(noticeRepository.findAll());
    }
}
